package Common;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteResources {
    public static HashMap<BufferedImage, String> imageToPath = new HashMap<>();
    public static HashMap<String, BufferedImage> pathToImage = new HashMap<>();
    public static boolean foregroundSprites = false;
    public static String selectedSprite = null;

    public static void addImage(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            if (img.getHeight() % 16 == 0 && img.getWidth() % 16 == 0) {
                String imagePath = path.replace("\\", "/");
                SpriteResources.imageToPath.put(img, imagePath);
                SpriteResources.pathToImage.put(imagePath, img);
            }
        } catch (Exception e) {
        }
    }
}
