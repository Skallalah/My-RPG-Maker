import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static java.lang.Integer.max;

public class MapPanel extends JPanel {
    BufferedImage defaultTile;

    public MapPanel(String defaultTilePath) {
        setBackground(Color.black);
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
        for (;y < 900; y += 17) {
            for (; x < 300; x += 17)
                g.drawImage(defaultTile, x, y, null);
            x = 2;
        }
    }
}
