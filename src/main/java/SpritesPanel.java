import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static java.lang.Integer.max;

public class SpritesPanel extends JPanel {
    ArrayList<BufferedImage> images;
    Dimension dimension;

    public SpritesPanel(Dimension dimension){
        setDoubleBuffered(true);
        this.dimension = dimension;
        setPreferredSize(dimension);
        images = new ArrayList<BufferedImage>();
        loadSprites("resources/sprites");
    }

    private void loadSprites(String path) {
        File folder = new File(path);
        for(File fileEntry : folder.listFiles())
            if (!fileEntry.isDirectory()) {
                try {
                    images.add(ImageIO.read(fileEntry));
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
