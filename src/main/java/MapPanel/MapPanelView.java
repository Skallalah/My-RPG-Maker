package MapPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.max;

public class MapPanelView extends JPanel {
    BufferedImage defaultTile;

    int width, height;
    boolean gridDisplay;

    public MapPanelView(String defaultTilePath, int width, int height) {
        setBackground(Color.black);
        setPreferredSize(new Dimension(width * 16, height * 16));

        this.width = width;
        this.height = height;
        gridDisplay = true;

        try {
            defaultTile = ImageIO.read(new File(defaultTilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void toggleGridDisplay() {
        gridDisplay = !gridDisplay;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int x = 0;
        int y = 0;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.drawImage(defaultTile, x, y, null);
                x += 16;
            }
            x = 0;
            y += 16;
        }

        if (gridDisplay) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(0, 0, 0, 100));
            for (x = 16; x < width * 16; x += 16)
                g2.draw(new Line2D.Float(x, 0, x, height * 16));
            for (y = 16; y < height * 16; y += 16)
                g2.draw(new Line2D.Float(0, y, width * 16, y));
        }
    }
}
