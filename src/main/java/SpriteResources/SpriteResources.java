package SpriteResources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class SpriteResources {
    static public ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
    static public ArrayList<String> imagesPaths = new ArrayList<String>();
    static public int selectedIndex;

    static public void loadSprites(String path) throws Exception {
        File folder = new File(path);
        for(File fileEntry : folder.listFiles())
            if (!fileEntry.isDirectory()) {
                try {
                    images.add(ImageIO.read(fileEntry));
                    imagesPaths.add(fileEntry.getPath());
                    BufferedImage img = images.get(images.size() - 1);
                    // MULTIPLE OF 16
                    if (img.getHeight() % 16 != 0 || img.getWidth() % 16 != 0)
                        throw new Exception();
                    // CATCH IF IT IS NOT AN IMAGE
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                loadSprites(fileEntry.getPath());
            }
    }
}
