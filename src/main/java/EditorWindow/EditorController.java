package EditorWindow;

import Common.Executor;
import Common.Observer;
import Common.SpriteResources;
import GameWindow.GameController;
import GameWindow.GameModel;
import GameWindow.GameView;
import MapPanel.MapPanelView;
import NewFrame.NewController;
import NewFrame.NewModel;
import NewFrame.NewView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

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
                    view.dispose();
                    System.exit(0);
                });
            }
        });

        view.getRemoveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    SpriteResources.selectedSprite = "remove";
                    view.setCursor(Cursor.getDefaultCursor());
                });
            }
        });

        view.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Executor.executor.submit(() -> {
                    GameModel playModel = new GameModel(model.getCurrentWorld());
                    GameView view_ = new GameView(playModel);
                    GameController control = new GameController(playModel, view_);
                    view_.repaint();
                    control.control();
                });
            }
        });
    }

    private void newAction() {
        Executor.executor.submit(() -> {
            NewView newView = new NewView();
            NewModel newModel = new NewModel();
            NewController newController = new NewController(newView, newModel, view);
            newController.control();
        });
    }

    private void openAction() {
        Executor.executor.submit(() -> {
            JFileChooser fileChooser = new JFileChooser();
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
