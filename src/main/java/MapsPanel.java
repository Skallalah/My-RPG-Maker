import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapsPanel extends JTree{

    public MapsPanel(DefaultMutableTreeNode top, Dimension dimension) {
        super(top);
        setPreferredSize(dimension);
        loadSprites(top, "resources/maps");
    }



    private void loadSprites(DefaultMutableTreeNode top, String path) {
        File maps_wrld = new File(path);
        for(File fileEntry : maps_wrld.listFiles())
            if (!fileEntry.isDirectory()) {
            String file = fileEntry.getName();
            if (wrld_extension(file))
                top.add(new DefaultMutableTreeNode(file.substring(0, file.indexOf("."))));
            }
            else {
            DefaultMutableTreeNode directory = new DefaultMutableTreeNode(fileEntry.getName());
            top.add(directory);
                loadSprites(directory, fileEntry.getPath());
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
