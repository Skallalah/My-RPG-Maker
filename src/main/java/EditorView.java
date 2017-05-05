import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class EditorView extends JFrame {

    JButton openButton;
    JButton newButton;
    JButton saveButton;

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
        add(toolBar, BorderLayout.NORTH);

        SpritesPanel spritesPanel = new SpritesPanel(new Dimension(300, 500));
        JScrollPane topLeftPanel = new JScrollPane(spritesPanel);

        MapsPanel mapsPanel = new MapsPanel(new DefaultMutableTreeNode("Maps"), new Dimension(300, 400));
        JScrollPane bottomLeftPanel = new JScrollPane(mapsPanel);


        JSplitPane leftPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topLeftPanel, bottomLeftPanel);

        JTabbedPane tabbedPane = new JTabbedPane();
        MapPanel mapPanel = new MapPanel("resources/sprites/backgroundTile/grass.png");
        tabbedPane.addTab("Map 1", new JScrollPane(mapPanel));
        tabbedPane.addTab("Map 2", new JPanel());
        JScrollPane rightPanel = new JScrollPane(tabbedPane);

        JSplitPane splitPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        add(splitPanel);

        setJMenuBar(menuBar);
        setVisible(true);

    }

    private ImageIcon getIcon(String path) {
        return new ImageIcon(new ImageIcon("resources/icons/" + path).getImage().getScaledInstance(18, 18,  java.awt.Image.SCALE_SMOOTH));
    }

    public JButton getOpenButton() {
        return openButton;
    }
    public JButton getNewButton() {
        return newButton;
    }
    public JButton getSaveButton() {
        return saveButton;
    }

}
