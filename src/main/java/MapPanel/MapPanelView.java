package MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import Common.Observer;
import Common.SpriteResources;
import EditorWindow.EditorModel;
import Game.GameForeground;

public class MapPanelView extends JPanel implements Observer {
    MapPanelModel model;

    public MapPanelView(MapPanelModel model) {
        this.model = model;
        model.addObserver(this);

        setBackground(Color.black);
        setPreferredSize(new Dimension(model.getWidth() * 16, model.getHeight() * 16));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawTiles(g);
        drawObjects(g);
        if (EditorModel.getSelf().getGridDisplay())
            drawGrid(g);
    }

    private void drawTiles(Graphics g) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                String path = model.map.getPathTile(j, i);
                BufferedImage image = SpriteResources.pathToImage.get(path);
                g.drawImage(image, x, y, null);
                x += 16;
            }
            x = 0;
            y += 16;
        }
    }

    private void drawObjects(Graphics g) {
        for (GameForeground obj : model.map.getObjects()) {
            String path = obj.getPath();
            BufferedImage image = SpriteResources.pathToImage.get(path);
            g.drawImage(image, obj.getX() * 16, obj.getY() * 16, null);
        }
    }

    private void drawGrid(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 0, 0, 100));
        for (int x = 16; x < model.getWidth() * 16; x += 16)
            g2.draw(new Line2D.Float(x, 0, x, model.getHeight() * 16));
        for (int y = 16; y < model.getHeight() * 16; y += 16)
            g2.draw(new Line2D.Float(0, y, model.getWidth() * 16, y));
    }

    @Override
    public void update(String str) {
        if (str.equals("Repaint")) {
            revalidate();
            repaint();
        }
    }
}
