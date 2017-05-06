import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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

        int x = (gridDisplay ? 1 : 0);
        int y = x;

        int offset = 16 + (gridDisplay ? 1 : 0);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                g.drawImage(defaultTile, x, y, null);
                x += offset;
            }
            x = (gridDisplay ? 1 : 0);
            y += offset;
        }
    }
}
