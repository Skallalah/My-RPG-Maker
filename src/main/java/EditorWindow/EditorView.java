package EditorWindow;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import MapPanel.*;
import SpritesPanel.*;

public class EditorView extends JFrame {

    JButton newButton;
    JButton openButton;
    JButton saveButton;
    JButton walkableButton;
    JButton notwalkableButton;

    public EditorView() {
        setTitle("MyRPGMaker");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");
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
        JButton removeButton = new JButton(remove_b);
        toolBar.add(removeButton);
        ImageIcon undo_b = getIcon("undo.png");
        JButton undoButton = new JButton(undo_b);
        toolBar.add(undoButton);
        ImageIcon play_b = getIcon("play.png");
        JButton playButton = new JButton(play_b);
        toolBar.add(playButton);
        ImageIcon walkable_b = getIcon("walkable.png");
        walkableButton = new JButton(walkable_b);
        toolBar.add(walkableButton);
        ImageIcon notwalkable_b = getIcon("notwalkable.png");
        notwalkableButton = new JButton(notwalkable_b);
        toolBar.add(notwalkableButton);

        add(toolBar, BorderLayout.NORTH);

        SpritesPanelModel spritesPanelModel = new SpritesPanelModel();
        SpritesPanelView spritesPanelView = new SpritesPanelView(new Dimension(300, 500));
        SpritesPanelController spritesPanelController = new SpritesPanelController(spritesPanelModel, spritesPanelView);
        spritesPanelController.control();
        spritesPanelView.setBackground(Color.WHITE);
        JScrollPane topLeftPanel = new JScrollPane(spritesPanelView);

        MapsPanel mapsPanel = new MapsPanel(new DefaultMutableTreeNode("Maps"), new Dimension(300, 400));
        JScrollPane bottomLeftPanel = new JScrollPane(mapsPanel);


        JSplitPane leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topLeftPanel, bottomLeftPanel);

        JTabbedPane rightPanel = new JTabbedPane();
        MapPanelModel mapPanelModel = new MapPanelModel();
        MapPanelView mapPanelView = new MapPanelView("resources/sprites/backgroundTile/grass.png", 200, 200);
        MapPanelController mapPanelController = new MapPanelController(mapPanelModel, mapPanelView);
        mapPanelController.control();
        JScrollPane map = new JScrollPane(mapPanelView);
        map.setPreferredSize(new Dimension(0,0));
        rightPanel.addTab("Map 1", map);

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        add(splitPanel);

        setJMenuBar(menuBar);
        setVisible(true);

    }

    private ImageIcon getIcon(String path) {
        return new ImageIcon(new ImageIcon("resources/icons/" + path).getImage().getScaledInstance(18, 18,  java.awt.Image.SCALE_SMOOTH));
    }

    public JButton getNewButton() {
        return newButton;
    }
    public JButton getOpenButton() {
        return openButton;
    }
    public JButton getSaveButton() { return saveButton; }
    public JButton getWalkableButton() { return walkableButton; }
    public JButton getNotwalkableButton() { return notwalkableButton; }

}
