package SpritesPanel;

import Common.Observer;
import Common.SpriteResources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.max;

public class SpritesPanelView extends JPanel implements Observer {
    Dimension dimension;
    String pathFilter;

    public SpritesPanelView(Dimension dimension, String pathFilter){
        setBackground(Color.WHITE);
        setDoubleBuffered(true);
        this.dimension = dimension;
        this.pathFilter = pathFilter;
        setPreferredSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        int maxy = 0;
        for (BufferedImage img : SpriteResources.images) {
            if (SpriteResources.imageToPath.get(img).startsWith(pathFilter)) {
                if (x + img.getWidth() > dimension.getWidth()) {
                    x = 0;
                    y = maxy + 16;
                }
                g.drawImage(img, x, y, null);
                x += img.getWidth() + 16;
                maxy = max(maxy, img.getHeight());
            }
        }
        setPreferredSize(new Dimension(getWidth(), maxy + 100));
    }

    @Override
    public void update(String str) {

    }
}