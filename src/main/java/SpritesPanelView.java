import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.max;

public class SpritesPanelView extends JPanel {
    ArrayList<BufferedImage> images;
    Dimension dimension;

    public SpritesPanelView(Dimension dimension){
        setDoubleBuffered(true);
        this.dimension = dimension;
        setPreferredSize(dimension);
        images = new ArrayList<BufferedImage>();
        try {
            loadSprites("resources/sprites");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadSprites(String path) throws Exception {
        File folder = new File(path);
        for(File fileEntry : folder.listFiles())
            if (!fileEntry.isDirectory()) {
                try {
                    images.add(ImageIO.read(fileEntry));
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        int maxy = 0;
        for (BufferedImage img : images) {
            if (x + img.getWidth() > dimension.getWidth()) {
                x = 0;
                y = maxy + 16;
            }
            g.drawImage(img, x, y, null);
            x += img.getWidth() + 16;
            maxy = max(maxy, img.getHeight());
        }
    }
}
