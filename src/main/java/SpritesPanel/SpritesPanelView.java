package SpritesPanel;
import SpriteResources.SpriteResources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

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


        int x = 0;
        int y = 0;
        int maxy = 0;
        for (BufferedImage img : SpriteResources.images) {
            if (x + img.getWidth() > dimension.getWidth()) {
                x = 0;
                y = maxy + 16;
            }
            ImageIcon image = new ImageIcon(img);
            JLabel label = new JLabel(image);
            label.setLocation(x, y);
            add(label);
            x += img.getWidth() + 16;
            maxy = max(maxy, img.getHeight());
        }
    }

}