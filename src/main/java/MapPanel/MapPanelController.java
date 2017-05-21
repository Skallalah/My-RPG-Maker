package MapPanel;

import Common.EditorProperties;
import Common.Executor;
import Common.SpriteResources;
import EventFrame.EventController;
import EventFrame.EventModel;
import EventFrame.EventView;

import javax.swing.text.Position;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapPanelController {
    private MapPanelModel model;
    private MapPanelView view;

    public MapPanelController(MapPanelModel model, MapPanelView view) {
        this.model = model;
        this.view = view;
    }

    public void control() {
        view.addMouseListener (new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                Executor.executor.submit(() -> {
                        clickAction(e.getX() / 16, e.getY() / 16);
                });
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                Executor.executor.submit(() -> {
                    releaseAction(e.getX() / 16, e.getY() / 16);
                });
            }
        });

        view.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Executor.executor.submit(() -> {
                    dragAction(e.getX() / 16, e.getY() / 16);
                });
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                model.setMouseOnPanel(new Point(e.getX() / 16, e.getY() / 16));
                model.repaint();
            }
        });
    }

    private void dragAction(int x, int y) {
        if (SpriteResources.selectedSprite.equals("select"))
            model.release(x, y);
        else
            action(x, y);
    }

    private void clickAction(int x, int y) {
        if (SpriteResources.selectedSprite.equals("select"))
            model.select(x, y);
        else if (SpriteResources.selectedSprite.equals("player")) {
            EditorProperties.playerPosition = new Point(x, y);
            EditorProperties.playerMapId = EditorProperties.currentMap.getId();
            model.repaint();
        } else if (SpriteResources.selectedSprite.equals("event")) {
            EditorProperties.x = x;
            EditorProperties.y = y;
            eventAction();
        }
        else
            action(x, y);
    }

    private void action(int x, int y) {
        if (SpriteResources.selectedSprite.equals("remove"))
            model.removeObjects(x,y);
        else if (SpriteResources.selectedSprite.equals("tileRemove"))
            model.removeTile(x, y);
        else if (SpriteResources.selectedSprite.equals("move"))
            model.grab(x, y);
        else
            model.drop(x, y);
    }

    private void releaseAction(int x, int y) {
        if (SpriteResources.selectedSprite.equals("select"))
            model.release(x, y);
    }

    private void eventAction() {
        Executor.executor.submit(() -> {
            EventModel eventModel = new EventModel();
            EventView eventView = new EventView(eventModel);
            EventController eventController = new EventController(eventView, eventModel);
            eventController.control();
        });
    }
}
