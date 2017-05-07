package SpriteResources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteResources {
    static public ArrayList<BufferedImage> images = new ArrayList<>();
    static public HashMap<BufferedImage, String> imageToPath = new HashMap<>();
    static public HashMap<String, BufferedImage> pathToImage = new HashMap<>();
    static public String selectedSprite = null;

    static public void loadSprites(String path) throws Exception {
        File folder = new File(path);
        for(File fileEntry : folder.listFiles())
            if (!fileEntry.isDirectory()) {
                try {
                    BufferedImage img = ImageIO.read(fileEntry);
                    images.add(img);
                    imageToPath.put(img, fileEntry.getPath());
                    pathToImage.put(fileEntry.getPath(), img);
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
