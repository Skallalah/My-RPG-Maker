package EditorWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

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
    JButton moveButton;
    JButton selectButton;
    JButton removeButton;
    JButton tileEraserButton;
    JButton walkableButton;
    JButton notwalkableButton;
    JButton rainButton;
    JButton snowButton;
    JButton sunButton;
    JButton gridButton;
    JButton undoButton;
    JButton playButton;
    JButton characButton;
    JButton recyclebinButton;
    JButton addTileButton;
    JButton addObjectButton;
    JButton eventButton;
    JButton exitButton;
    JButton searchButton;
    JTextField barField;
    JCheckBox backgroundCheck;
    JCheckBox foregroundCheck;
    JMenuItem newItem;
    JMenuItem openItem;
    JMenuItem saveItem;
    JMenuItem exitItem;
    JMenuItem undoItem;
    JMenuItem redoItem;

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
        undoItem = new JMenuItem("Undo");
        redoItem = new JMenuItem("Redo");
        editMenu.add(undoItem);
        editMenu.add(redoItem);
        menuBar.add(editMenu);

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
        ImageIcon move_b = getIcon("move.png");
        moveButton = new JButton(move_b);
        toolBar.add(moveButton);
        ImageIcon select_b = getIcon("selection.png");
        selectButton = new JButton(select_b);
        toolBar.add(selectButton);
        ImageIcon remove_b = getIcon("eraser.png");
        removeButton = new JButton(remove_b);
        toolBar.add(removeButton);
        ImageIcon remove_tile_b = getIcon("tile-eraser.png");
        tileEraserButton = new JButton(remove_tile_b);
        toolBar.add(tileEraserButton);
        ImageIcon undo_b = getIcon("undo.png");
        undoButton = new JButton(undo_b);
        toolBar.add(undoButton);
        ImageIcon play_b = getIcon("play.png");
        playButton = new JButton(play_b);
        toolBar.add(playButton);
        ImageIcon charac_b = getIcon("character.png");
        characButton = new JButton(charac_b);
        toolBar.add(characButton);
        ImageIcon walkable_b = getIcon("walkable.png");
        walkableButton = new JButton(walkable_b);
        toolBar.add(walkableButton);
        ImageIcon notwalkable_b = getIcon("notwalkable.png");
        notwalkableButton = new JButton(notwalkable_b);
        toolBar.add(notwalkableButton);
        ImageIcon rain_b = getIcon("rain.png");
        rainButton = new JButton(rain_b);
        toolBar.add(rainButton);
        ImageIcon snow_b = getIcon("snow.png");
        snowButton = new JButton(snow_b);
        toolBar.add(snowButton);
        ImageIcon sun_b = getIcon("sun.png");
        sunButton = new JButton(sun_b);
        toolBar.add(sunButton);
        ImageIcon grid_b = getIcon("grid.png");
        gridButton = new JButton(grid_b);
        toolBar.add(gridButton);
        ImageIcon recyclebin_b = getIcon("recyclebin.png");
        recyclebinButton = new JButton(recyclebin_b);
        toolBar.add(recyclebinButton);
        ImageIcon addTile_b = getIcon("addtile.png");
        addTileButton = new JButton(addTile_b);
        toolBar.add(addTileButton);
        ImageIcon addObject_b = getIcon("addobject.png");
        addObjectButton = new JButton(addObject_b);
        toolBar.add(addObjectButton);
        ImageIcon event_b = getIcon("event.png");
        eventButton = new JButton(event_b);
        toolBar.add(eventButton);
        ImageIcon exit_b = getIcon("exit.png");
        exitButton = new JButton(exit_b);
        toolBar.add(exitButton);


        add(toolBar, BorderLayout.NORTH);

        spritesPanelModel1 = new SpritesPanelModel("tests/resources/sprites/backgroundTile", false);
        SpritesPanelView spritesPanelView1 = new SpritesPanelView(spritesPanelModel1);
        SpritesPanelController spritesPanelController1 = new SpritesPanelController(spritesPanelModel1, spritesPanelView1);
        spritesPanelController1.control();
        JScrollPane topLeftPanel1 = new JScrollPane(spritesPanelView1);
        topLeftPanel1.setPreferredSize(new Dimension(320, 200));

        spritesPanelModel2 = new SpritesPanelModel("tests/resources/sprites/foregroundObject", true);
        SpritesPanelView spritesPanelView2 = new SpritesPanelView(spritesPanelModel2);
        SpritesPanelController spritesPanelController2 = new SpritesPanelController(spritesPanelModel2, spritesPanelView2);
        spritesPanelController2.control();
        JScrollPane topLeftPanel2 = new JScrollPane(spritesPanelView2);
        topLeftPanel2.setPreferredSize(new Dimension(320, 200));

        JSplitPane topLeftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topLeftPanel1, topLeftPanel2);
        topLeftPanel.setResizeWeight(0.5);

        MapsPanel mapsPanel = new MapsPanel(new DefaultMutableTreeNode("Maps"));
        JScrollPane bottomLeftPanel = new JScrollPane(mapsPanel);

        JPanel searchbar = new JPanel();
        searchbar.setLayout(new GridLayout(2, 2));

        searchButton = new JButton("Search");
        searchbar.add(searchButton);

        barField = new JTextField();
        searchbar.add(barField);

        backgroundCheck = new JCheckBox("Background Tiles");
        searchbar.add(backgroundCheck);
        foregroundCheck = new JCheckBox("Foreground Tiles");
        searchbar.add(foregroundCheck);



        JSplitPane spritePanelLeft = new JSplitPane(JSplitPane.VERTICAL_SPLIT, searchbar, topLeftPanel);
        JSplitPane leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, spritePanelLeft, bottomLeftPanel);
        leftPanel.setResizeWeight(0.7);

        rightPanel = new JTabbedPane();

        model.setCurrentWorld(new GameWorld("world"));
        GameCharacter charac = new GameCharacter("player", "tests/resources/sprites/npc/ninja.png", new GameClass("Rogue"));
        charac.spawn_player(0, 0, 0);
        model.getCurrentWorld().setCharacter_(charac);
        GameMap map1 = new GameMap("Map", 200, 200, "tests/resources/sprites/backgroundTile/grass.png");
        model.getCurrentWorld().addMap(map1);

        MapPanelModel mapPanelModel = new MapPanelModel(model.getCurrentWorld().getMap(0));
        MapPanelView mapPanelView = new MapPanelView(mapPanelModel);
        MapPanelController mapPanelController = new MapPanelController(mapPanelModel, mapPanelView);
        mapPanelController.control();
        JScrollPane map = new JScrollPane(mapPanelView);
        rightPanel.addTab("Map", map);

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPanel.setResizeWeight(0.5);
        add(splitPanel);

        setJMenuBar(menuBar);
        setVisible(true);

        requestFocus();
    }

    public void setCursor() {
        Executor.executor.submit(() -> {
            if (SpriteResources.selectedSprite != null) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                BufferedImage img = SpriteResources.pathToImage.get(SpriteResources.selectedSprite);
                Cursor c = toolkit.createCustomCursor(img, new Point(img.getWidth() / 2, img.getHeight() / 2), "img");
                setCursor(c);
            } else
                setCursor(Cursor.getDefaultCursor());
        });
    }

    private ImageIcon getIcon(String path) {
        return new ImageIcon(new ImageIcon("resources/icons/" + path).getImage().getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH));
    }

    private void addAllMaps() {
        Hashtable<Integer, GameMap> maps = model.getCurrentWorld().getMaps();
        ArrayList<GameMap> list = new ArrayList<>(maps.values());
        for (int i = list.size()-1; i >= 0; i--) {
            GameMap map = list.get(i);
            MapPanelModel mapPanelModel = new MapPanelModel(map);
            MapPanelView mapPanelView = new MapPanelView(mapPanelModel);
            MapPanelController mapPanelController = new MapPanelController(mapPanelModel, mapPanelView);
            mapPanelController.control();
            JScrollPane newMap = new JScrollPane(mapPanelView);
            newMap.setPreferredSize(new Dimension(0, 0));
            rightPanel.addTab(map.getName(), newMap);
        }
    }

    public void setWalkableButtons(Walkable walkable) {
        if (walkable == Walkable.WALKABLE) {
            walkableButton.setOpaque(true);
            notwalkableButton.setOpaque(false);
            walkableButton.setEnabled(true);
            notwalkableButton.setEnabled(false);
        } else if (walkable == Walkable.NON_WALKABLE) {
            walkableButton.setOpaque(false);
            notwalkableButton.setOpaque(true);
            walkableButton.setEnabled(false);
            notwalkableButton.setEnabled(true);
        } else {
            walkableButton.setOpaque(true);
            notwalkableButton.setOpaque(true);
            walkableButton.setEnabled(true);
            notwalkableButton.setEnabled(true);
        }
    }

    private void errorOnFile() {
        JOptionPane.showMessageDialog(this,
                "This is not a valid world file.",
                "Error on world file",
                JOptionPane.ERROR_MESSAGE);
    }

    private void setEraserCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        File path = new File("resources/icons/eraser.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cursor c = toolkit.createCustomCursor(img, new Point(20, 60), "img");
        setCursor(c);
    }

    private void setCharacCursor() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        File path = new File("resources/icons/player.png");
        BufferedImage img = null;
        try {
            img = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Cursor c = toolkit.createCustomCursor(img, new Point(img.getWidth() / 2, img.getHeight() / 2), "img");
        setCursor(c);
    }

    private void exit() {
        dispose();
        System.exit(0);
    }

    private void errorOnPlayerPosition() {
        JFrame jframe = new JFrame();
        JOptionPane.showMessageDialog(jframe,
                "There is no player's spawn position.",
                "Error on spawn position",
                JOptionPane.ERROR_MESSAGE);
    }

    private void searchAction() {

    }

    public JButton getNewButton() {
        return newButton;
    }
    public JButton getOpenButton() {
        return openButton;
    }
    public JButton getSaveButton() { return saveButton; }
    public JButton getMoveButton() { return moveButton; }
    public JButton getSelectButton() { return selectButton; }
    public JButton getRemoveButton() { return removeButton; }
    public JButton getTileRemoveButton() { return tileEraserButton; }
    public JButton getWalkableButton() { return walkableButton; }
    public JButton getNotwalkableButton() { return notwalkableButton; }
    public JButton getRainButton() { return rainButton; }
    public JButton getSnowButton() { return snowButton; }
    public JButton getSunButton() { return sunButton; }
    public JButton getGridButton() { return gridButton; }
    public JButton getUndoButton() {
        return undoButton;
    }
    public JButton getPlayButton() {
        return playButton;
    }
    public JButton getCharacButton() {
        return characButton;
    }
    public JButton getRecycleBinButton() {
        return recyclebinButton;
    }
    public JButton getAddTileButton() {
        return addTileButton;
    }
    public JButton getAddObjectButton() {
        return addObjectButton;
    }
    public JButton getEventButton() {
        return eventButton;
    }
    public JButton getExitButton() {
        return exitButton;
    }
    public JButton getSearchButton() { return searchButton; }
    public JTextField getBarField() { return barField; }
    public JCheckBox getBackgroundCheck() { return backgroundCheck; }
    public JCheckBox getForegroundCheck() { return foregroundCheck; }
    public JMenuItem getNewMenuItem() { return newItem; }
    public JMenuItem getOpenMenuItem() {
        return openItem;
    }
    public JMenuItem getSaveMenuItem() {
        return saveItem;
    }
    public JMenuItem getExitMenuItem() {
        return exitItem;
    }
    public JMenuItem getUndoMenuItem() {
        return undoItem;
    }
    public JMenuItem getRedoMenuItem() {
        return redoItem;
    }
    public SpritesPanelModel getTileModel() { return spritesPanelModel1; }
    public SpritesPanelModel getObjectModel() { return spritesPanelModel2; }
    public static JTabbedPane getMapPanel() { return rightPanel; }
   // public Component getRightPanel() { return rightPanel.getSelectedComponent(); }

    @Override
    public void update(String str) {
        if (str.equals("repaint")) {
            revalidate();
            repaint();
        }
        else if (str.equals("setCursor")) {
            setCursor();
        }
        else if (str.equals("removeAllTabs")) {
            rightPanel.removeAll();
        }
        else if (str.equals("addAllMaps")) {
            addAllMaps();
        }
        else if (str.equals("walkable")) {
            setWalkableButtons(Walkable.WALKABLE);
        }
        else if (str.equals("nonwalkable")) {
            setWalkableButtons(Walkable.NON_WALKABLE);
        }
        else if (str.equals("walkable_none")) {
            setWalkableButtons(Walkable.NONE);
        }
        else if (str.equals("setEraserCursor")) {
            setEraserCursor();
        }
        else if (str.equals("setCharacCursor")) {
            setCharacCursor();
        }
        else if (str.equals("errorOnFile")) {
            errorOnFile();
        }
        else if (str.equals("exit")) {
            exit();
        }
        else if (str.equals("errorOnPlayerPosition")) {
            errorOnPlayerPosition();
        }
        else if (str.equals("search")) {
            searchAction();
        }
    }

    SpritesPanelModel spritesPanelModel1;
    SpritesPanelModel spritesPanelModel2;
    private static JTabbedPane rightPanel;
}
