package EditorWindow;

import Common.Executor;
import Common.History;
import Common.Observer;
import Common.SpriteResources;
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

    public EditorController(EditorModel model, EditorView view){
        this.model = model;
        this.view = view;
    }

    public void control(){

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
                    int currentIndex = EditorView.getMapPanel().getSelectedIndex();
                    String title = EditorView.getMapPanel().getTitleAt(currentIndex);
                    Hashtable<Integer, GameMap> maps = model.getCurrentWorld().getMaps();
                    for(Integer id : maps.keySet())
                        if (model.getCurrentWorld().getMap(id).getName().equals(title))
                            charac.spawn_player(id, SpriteResources.playerPosition.x, SpriteResources.playerPosition.y);

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
                    maps.values().remove(SpriteResources.mapToRender);
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
