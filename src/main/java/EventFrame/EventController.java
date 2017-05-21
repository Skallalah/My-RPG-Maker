package EventFrame;

import Common.EditorProperties;
import Common.Executor;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;
import Game.Events.LaunchDialogue;
import Game.Events.TeleportPlayer;
import Game.GameDialogue;
import Game.GameForeground;
import Game.GameObject;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by najjaj_k on 5/16/17.
 */
public class EventController {

    EventView eventView;
    EventModel eventModel;

    public EventController(EventView eventView, EventModel eventModel) {
        this.eventView = eventView;
        this.eventModel = eventModel;
    }

    public void control() {

        eventView.getEvents().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    String event = (String) eventView.getEvents().getSelectedItem();
                    if (event.equals("[TELEPORTER] Teleport"))
                        eventModel.teleport();
                    else if (event.equals("[NPC] Dialog"))
                        eventModel.dialog();
                });
            }
        });

        eventView.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    String spritePath = eventView.getSprites().getSelectedItem().toString();
                    GameObject obj = new GameObject(EditorProperties.x, EditorProperties.y, EditorProperties.currentMap.getId());
                    obj.set_sprite(spritePath);

                    String eventType = eventView.getEvents().getSelectedItem().toString();
                    TeleportPlayer teleportScript = null;
                    LaunchDialogue dialogScript = null;
                    if (eventType.equals("[TELEPORTER] Teleport")) {
                        Integer x = Integer.parseInt(eventView.getPosX().getText());
                        Integer y = Integer.parseInt(eventView.getPosY().getText());
                        Integer mapId = Integer.parseInt(eventView.getMapId().getText());
                        teleportScript = new TeleportPlayer(mapId, 0, x, y);
                    }
                    else if (eventType.equals("[NPC] Dialog")) {
                        String dialog = eventView.getDialog().getText();
                        dialogScript = new LaunchDialogue(new GameDialogue(dialog));
                    }
                    String actionType = eventView.getActions().getSelectedItem().toString();
                    if (actionType.equals("On touch")) {
                        if (teleportScript != null)
                            obj.set_touch(teleportScript);
                        else
                            obj.set_touch(dialogScript);
                    }
                    else if (actionType.equals("On action")) {
                        if (teleportScript != null)
                            obj.set_interaction(teleportScript);
                        else
                            obj.set_interaction(dialogScript);
                    }
                    else {
                        if (teleportScript != null) {
                            obj.set_touch(teleportScript);
                            obj.set_interaction(teleportScript);
                        }
                        else {
                            obj.set_touch(dialogScript);
                            obj.set_interaction(dialogScript);
                        }
                    }

                    EditorProperties.currentMap.add_script(obj);
                    EditorModel.getSelf().repaint();
                    eventModel.doneAction();
                });
            }
        });

        eventView.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    eventModel.cancelAction();
                });
            }
        });

    }
}
