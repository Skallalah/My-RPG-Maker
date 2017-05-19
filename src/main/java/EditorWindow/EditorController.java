package EditorWindow;

import Common.*;
import EventFrame.EventController;
import EventFrame.EventModel;
import EventFrame.EventView;
import Game.CharacterPersonalization.GameClass;
import Game.GameCharacter;
import Game.GameMap;
import GameWindow.GameController;
import GameWindow.GameModel;
import GameWindow.GameView;
import MapPanel.MapPanelModel;
import MapPanel.MapPanelView;
import NewFrame.NewController;
import NewFrame.NewModel;
import NewFrame.NewView;
import com.sun.javafx.application.PlatformImpl;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Hashtable;

public class EditorController {
    private EditorModel model;
    private EditorView view;
    private boolean ctrlPressed;

    public EditorController(EditorModel model, EditorView view){
        this.model = model;
        this.view = view;
        ctrlPressed = false;
    }

    public void control(){

        view.addKeyListener (new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Z) {
                    Executor.executor.submit(() -> {
                        History.undo();
                        model.repaint();
                    });
                }
                else if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_Y) {
                    Executor.executor.submit(() -> {
                        History.redo();
                        model.repaint();
                    });
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        view.getNewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newAction();
            }
        });

        view.getOpenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                openAction();
            }
        });

        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                saveAction();
            }
        });

        view.getMoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    SpriteResources.selectedSprite = "move";
                });
            }
        });

        view.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    SpriteResources.selectedSprite = "select";
                });
            }
        });

        view.getWalkableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    model.setWalkable(Walkable.WALKABLE);
                });
            }
        });

        view.getNotwalkableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    model.setWalkable(Walkable.NON_WALKABLE);
                });
            }
        });

        view.getGridButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    model.toggleGridDisplay();
                });
            }
        });

        view.getNewMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newAction();
            }
        });

        view.getOpenMenuItem().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    openAction();
                });
            }
        });

        view.getExitButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    model.exit();
                });
            }
        });

        view.getSaveMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    saveAction();
                });
            }
        });

        view.getExitMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    model.exit();
                });
            }
        });

        view.getUndoMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    History.undo();
                    model.repaint();
                });
            }
        });

        view.getRedoMenuItem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    History.redo();
                    model.repaint();
                });
            }
        });

        view.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    SpriteResources.selectedSprite = "remove";
                    model.setEraserCursor();
                });
            }
        });

        view.getUndoButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    History.undo();
                    model.repaint();
                });
            }
        });

        view.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    GameCharacter charac = new GameCharacter("player", "resources/sprites/npc/ninja.png", new GameClass("Rogue"));

                    if (EditorProperties.playerPosition == null) {
                        model.errorOnPlayerPosition();
                        return;
                    }
                    int x = EditorProperties.playerPosition.x;
                    int y = EditorProperties.playerPosition.y;
                    int mapId = EditorProperties.playerMapId;
                    GameMap map = model.getCurrentWorld().getMap(mapId);
                    if (x < 0 || x > map.getWidth() - 1 || y < 0 || y > map.getHeight() - 1) {
                        model.notifyObserver("errorOnPlayerPosition");
                        return;
                    }

                    charac.spawn_player(mapId, x, y);
                    model.getCurrentWorld().setCharacter_(charac);
                    GameModel playModel = new GameModel(model.getCurrentWorld());
                    GameView view_ = new GameView(playModel);
                    GameController control = new GameController(playModel, view_);
                    control.control();
                    model.repaint();
                });
            }
        });

        view.getCharacButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    Executor.executor.submit(() -> {
                        SpriteResources.selectedSprite = "player";
                        model.setCharacCursor();
                    });
                });
            }
        });

        view.getRecycleBinButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    EditorView.getMapPanel().remove(EditorView.getMapPanel().getSelectedComponent());

                    Hashtable<Integer, GameMap> maps = model.getCurrentWorld().getMaps();
                    maps.values().remove(EditorProperties.currentMap);
                });
            }
        });

        view.getAddTileButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Add a new Background image");
                    int returnVal = fileChooser.showOpenDialog(view);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        view.getTileModel().addImage(file.getPath());
                    }
                });
            }
        });

        view.getAddObjectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Add a new Foreground image");
                    int returnVal = fileChooser.showOpenDialog(view);
                    if(returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        view.getObjectModel().addImage(file.getPath());
                    }
                });
            }
        });

        view.getRainButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    EditorProperties.weatherTile = "resources/sprites/weather/rain.png";
                    MapPanelModel.fillSelectionWithProperties(false);
                });
            }
        });

        view.getSnowButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    EditorProperties.weatherTile = "resources/sprites/weather/snow.png";
                    MapPanelModel.fillSelectionWithProperties(false);
                });
            }
        });

        view.getSunButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    EditorProperties.weatherTile = null;
                    MapPanelModel.fillSelectionWithProperties(false);
                });
            }
        });

        view.getEventButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    SpriteResources.selectedSprite = "event";
                });
            }
        });
    }

    private void newAction() {
        Executor.executor.submit(() -> {
            NewModel newModel = new NewModel();
            NewView newView = new NewView(newModel);
            NewController newController = new NewController(newView, newModel);
            newController.control();
        });
    }

    private void openAction() {
        Executor.executor.submit(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Open a file");
            int returnVal = fileChooser.showOpenDialog(view);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                model.openFile(file.getPath());
            }
        });
    }

    private void saveAction() {
        Executor.executor.submit(() -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose a file that you want to save");
            int returnVal = fileChooser.showSaveDialog(view);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                model.saveFile(file.getPath(), view);
            }
        });
    }
}
