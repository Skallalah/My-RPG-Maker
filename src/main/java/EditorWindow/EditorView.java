package EditorWindow;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.image.BufferedImage;

import Common.Executor;
import Common.Observer;
import Common.SpriteResources;
import Game.CharacterPersonalization.GameClass;
import Game.GameCharacter;
import Game.GameMap;
import Game.GameWorld;
import MapPanel.*;
import SpritesPanel.*;
import NewFrame.*;

public class EditorView extends JFrame implements Observer {

    EditorModel model;

    JButton newButton;
    JButton openButton;
    JButton saveButton;
    JButton removeButton;
    JButton walkableButton;
    JButton notwalkableButton;
    JButton gridButton;
    JButton playButton;
    JMenuItem newItem;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JTabbedPane rightPanel;


    public EditorView(EditorModel model) {
        this.model = model;
        model.addObserver(this);

        setTitle("MyRPGMaker");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        newItem = new JMenuItem("New");
        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("Exit");
        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem undoItem = new JMenuItem("Undo");
        JMenuItem redoItem = new JMenuItem("Redo");
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        menuBar.add(editMenu);

        JMenu launchMenu = new JMenu("Game");
        JMenuItem launchItem = new JMenuItem("Launch");
        launchItem.add(launchMenu);
        menuBar.add(launchMenu);

        JMenu helpMenu = new JMenu("Help");
        JMenuItem commandsItem = new JMenuItem("Commands");
        JMenuItem moreItem = new JMenuItem("More");
        helpMenu.add(commandsItem);
        helpMenu.add(moreItem);
        menuBar.add(helpMenu);
        
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.X_AXIS));
        ImageIcon new_b = getIcon("new.png");
        newButton = new JButton(new_b);
        toolBar.add(newButton);
        ImageIcon open_b = getIcon("open.png");
        openButton = new JButton(open_b);
        toolBar.add(openButton);
        ImageIcon save_b = getIcon("save.png");
        saveButton = new JButton(save_b);
        toolBar.add(saveButton);
        ImageIcon cut_b = getIcon("cut.png");
        JButton cutButton = new JButton(cut_b);
        toolBar.add(cutButton);
        ImageIcon copy_b = getIcon("copy.png");
        JButton copyButton = new JButton(copy_b);
        toolBar.add(copyButton);
        ImageIcon paste_b = getIcon("paste.png");
        JButton pasteButton = new JButton(paste_b);
        toolBar.add(pasteButton);
        ImageIcon remove_b = getIcon("remove.png");
        removeButton = new JButton(remove_b);
        toolBar.add(removeButton);
        ImageIcon undo_b = getIcon("undo.png");
        JButton undoButton = new JButton(undo_b);
        toolBar.add(undoButton);
        ImageIcon play_b = getIcon("play.png");
        playButton = new JButton(play_b);
        toolBar.add(playButton);
        ImageIcon walkable_b = getIcon("walkable.png");
        walkableButton = new JButton(walkable_b);
        toolBar.add(walkableButton);
        ImageIcon notwalkable_b = getIcon("notwalkable.png");
        notwalkableButton = new JButton(notwalkable_b);
        toolBar.add(notwalkableButton);
        ImageIcon grid_b = getIcon("grid.png");
        gridButton = new JButton(grid_b);
        toolBar.add(gridButton);

        add(toolBar, BorderLayout.NORTH);

        try {
            SpriteResources.loadSprites("resources/sprites");
        } catch (Exception e) {
            e.printStackTrace();
        }

        SpritesPanelModel spritesPanelModel1 = new SpritesPanelModel(false);
        SpritesPanelView spritesPanelView1 = new SpritesPanelView(new Dimension(280, 500), "resources/sprites/backgroundTile");
        SpritesPanelController spritesPanelController1 = new SpritesPanelController(spritesPanelModel1, spritesPanelView1);
        spritesPanelController1.control();
        JScrollPane topLeftPanel1 = new JScrollPane(spritesPanelView1);

        SpritesPanelModel spritesPanelModel2 = new SpritesPanelModel(true);
        SpritesPanelView spritesPanelView2 = new SpritesPanelView(new Dimension(280, 500), "resources/sprites/foregroundObject");
        SpritesPanelController spritesPanelController2 = new SpritesPanelController(spritesPanelModel2, spritesPanelView2);
        spritesPanelController2.control();
        JScrollPane topLeftPanel2 = new JScrollPane(spritesPanelView2);

        JSplitPane topLeftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topLeftPanel1, topLeftPanel2);

        MapsPanel mapsPanel = new MapsPanel(new DefaultMutableTreeNode("Maps"));
        JScrollPane bottomLeftPanel = new JScrollPane(mapsPanel);

        JSplitPane leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topLeftPanel, bottomLeftPanel);

        rightPanel = new JTabbedPane();

        model.setCurrentWorld(new GameWorld("world"));
        GameCharacter charac = new GameCharacter("player", "resources/sprites/npc/ninja.png", new GameClass("Rogue"));
        charac.spawn_player(0, 0, 0);
        model.getCurrentWorld().setCharacter_(charac);
        GameMap map1 = new GameMap("Map1", 200, 200, "resources/sprites/backgroundTile/grass.png");
        model.getCurrentWorld().addMap(map1);

        MapPanelModel mapPanelModel = new MapPanelModel(model.getCurrentWorld().getMap(0));
        MapPanelView mapPanelView = new MapPanelView(mapPanelModel);
        MapPanelController mapPanelController = new MapPanelController(mapPanelModel, mapPanelView);
        mapPanelController.control();
        JScrollPane map = new JScrollPane(mapPanelView);
        rightPanel.addTab("Map", map);
        rightPanel.setPreferredSize(new Dimension(500, 850));

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        add(splitPanel);

        setJMenuBar(menuBar);
        setVisible(true);

    }

    public void resetCursor() {
        Executor.executor.submit(() -> {
            if (SpriteResources.selectedSprite != null) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                BufferedImage img = SpriteResources.pathToImage.get(SpriteResources.selectedSprite);
                Cursor c = toolkit.createCustomCursor(img, new Point(img.getWidth() / 2, img.getHeight() / 2), "img");
                setCursor(c);
            }
            else
                setCursor(Cursor.getDefaultCursor());
        });
    }

    private ImageIcon getIcon(String path) {
        return new ImageIcon(new ImageIcon("resources/icons/" + path).getImage().getScaledInstance(18, 18,  java.awt.Image.SCALE_SMOOTH));
    }

    public void setWalkableButtons(boolean walkable) {
        if (walkable) {
            walkableButton.setEnabled(false);
            notwalkableButton.setEnabled(true);
            walkableButton.setOpaque(true);
        }
        else {
            walkableButton.setOpaque(false);
            walkableButton.setEnabled(true);
            notwalkableButton.setEnabled(true);
        }
        revalidate();
        repaint();
    }

    public JButton getNewButton() {
        return newButton;
    }
    public JButton getOpenButton() {
        return openButton;
    }
    public JButton getSaveButton() { return saveButton; }
    public JButton getRemoveButton() { return removeButton; }
    public JButton getWalkableButton() { return walkableButton; }
    public JButton getNotwalkableButton() { return notwalkableButton; }
    public JButton getGridButton() { return gridButton; }
    public JButton getPlayButton() {
        return playButton;
    }
    public JMenuItem getNewMenuItem() {
        return newItem;
    }
    public JMenuItem getOpenMenuItem() {
        return openItem;
    }
    public JMenuItem getSaveMenuItem() {
        return saveItem;
    }
    public JMenuItem getExitMenuItem() {
        return exitItem;
    }
    public JTabbedPane getMapPanel() { return rightPanel; }

    @Override
    public void update(String str) {
        if (str.equals("repaint")) {
            revalidate();
            repaint();
        }
        else if (str.equals("removeAllTabs")) {
            rightPanel.removeAll();
        }
        else if (str.equals("walkable")) {
            setWalkableButtons(true);
        }
        else if (str.equals("nonwalkable")) {
            setWalkableButtons(false);
        }
    }
}
