import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.max;

public class MapPanel extends JPanel {
    BufferedImage defaultTile;

    int width, height;

    public MapPanel(String defaultTilePath, int width, int height) {
        setBackground(Color.black);
        setPreferredSize(new Dimension(width * 16, height * 16));

        this.width = width;
        this.height = height;

        try {
            defaultTile = ImageIO.read(new File(defaultTilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        int x = 2;
        int y = 2;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.drawImage(defaultTile, x, y, null);
                x += 17;
            }
            x = 2;
            y += 17;
        }
    }
}
