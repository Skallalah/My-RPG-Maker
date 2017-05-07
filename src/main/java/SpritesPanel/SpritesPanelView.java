package SpritesPanel;
import SpriteResources.SpriteResources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.max;

public class SpritesPanelView extends JPanel {
    Dimension dimension;

    public SpritesPanelView(Dimension dimension){
        setDoubleBuffered(true);
        this.dimension = dimension;
        setPreferredSize(dimension);
        try {
            SpriteResources.loadSprites("resources/sprites");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        int maxy = 0;
        for (BufferedImage img : SpriteResources.images) {
            if (x + img.getWidth() > dimension.getWidth()) {
                x = 0;
                y = maxy + 16;
            }
            g.drawImage(img, x, y, null);
            x += img.getWidth() + 16;
            maxy = max(maxy, img.getHeight());
        }
        System.out.println(SpriteResources.images.size());
    }
}