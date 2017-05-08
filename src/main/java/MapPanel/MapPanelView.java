package MapPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import Game.GameObject;
import SpriteResources.SpriteResources;

import static java.lang.Integer.max;

public class MapPanelView extends JPanel {
    MapPanelModel mapPanelModel;
    BufferedImage defaultTile;

    int width, height;
    String defaultTilePath;
    boolean gridDisplay;

    public MapPanelView(MapPanelModel mapPanelModel) {
        setBackground(Color.black);
        setPreferredSize(new Dimension(width * 16, height * 16));

        this.mapPanelModel = mapPanelModel;

        this.width = mapPanelModel.getWidth();
        this.height = mapPanelModel.getHeight();
        this.defaultTilePath = mapPanelModel.getDefaultTile();
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

    public void paintComponent(Graphics g) {
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

        drawObjects(g);


        if (gridDisplay)
            drawGrid(g);
    }

    private void drawObjects(Graphics g) {
        for (GameObject obj : mapPanelModel.map.getObjects()) {
            String path = obj.getPath();
            BufferedImage image = SpriteResources.pathToImage.get(path);
            g.drawImage(image, obj.getX() * 16, obj.getY() * 16, null);
        }
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 0, 0, 100));
        for (int x = 16; x < width * 16; x += 16)
            g2.draw(new Line2D.Float(x, 0, x, height * 16));
        for (int y = 16; y < height * 16; y += 16)
            g2.draw(new Line2D.Float(0, y, width * 16, y));
    }
}
