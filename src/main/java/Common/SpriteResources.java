package Common;

import EditorWindow.Walkable;
import Game.GameMap;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.text.Position;

public class SpriteResources {
    public static HashMap<BufferedImage, String> imageToPath = new HashMap<>();
    public static HashMap<String, BufferedImage> pathToImage = new HashMap<>();
    public static boolean foregroundSprites = false;
    public static String selectedSprite = null;
    public static Rectangle selection;
    public static int x;
    public static int y;
    public static GameMap mapToRender;
    public static Walkable walkable = Walkable.NONE;
    public static Point playerPosition;

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
