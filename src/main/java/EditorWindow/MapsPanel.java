package EditorWindow;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.HashMap;

public class MapsPanel extends JTree{

    HashMap<String, String> jtreePathToFilePath;

    public MapsPanel(DefaultMutableTreeNode top) {
        super(top);
        jtreePathToFilePath = new HashMap<>();

        setPreferredSize(new Dimension(280,450));

        ImageIcon imageIcon = new ImageIcon(new ImageIcon("resources/icons/icon_wrld.png").getImage().getScaledInstance(18, 18,  java.awt.Image.SCALE_SMOOTH));
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(imageIcon);

        setCellRenderer(renderer);

        loadMaps(top, "resources/maps");

        MouseListener ml = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                TreePath selPath = getPathForLocation(e.getX(), e.getY());
                if(e.getClickCount() == 2) {
                    String jtreePath = computeJtreePath(selPath.getPath());

                    String filePath = jtreePathToFilePath.get(jtreePath);
                    if (filePath != null) {
                        EditorModel.getSelf().openFile(filePath);
                    }
                }
            }
        };
        addMouseListener(ml);
    }

    private String computeJtreePath(Object elements[]) {
        String jtreePath = new String();
        for (int i = 0, n = elements.length; i < n; i++)
            jtreePath += "/" + elements[i];
        return jtreePath;
    }

    private void loadMaps(DefaultMutableTreeNode top, String path) {
        File maps_wrld = new File(path);
        for(File fileEntry : maps_wrld.listFiles())
            if (!fileEntry.isDirectory()) {
                String file = fileEntry.getName();
                if (wrld_extension(file)) {
                    DefaultMutableTreeNode fileItem = new DefaultMutableTreeNode(file.substring(0, file.indexOf(".")));
                    top.add(fileItem);
                    String jtreePath = computeJtreePath(fileItem.getPath());
                    jtreePathToFilePath.put(jtreePath, path + "/" + fileEntry.getName());
                }
            }
            else {
                DefaultMutableTreeNode directory = new DefaultMutableTreeNode(fileEntry.getName());
                top.add(directory);
                loadMaps(directory, fileEntry.getPath());
            }
    }

    private boolean wrld_extension(String path) {
        int i = path.lastIndexOf('.');
        if (i > 0) {
            String extension = path.substring(i+1);
            return extension.equals("wrld");
        }
        else
            return false;
    }

}
