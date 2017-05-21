package MapPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.Event;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import Common.*;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;
import Game.GameForeground;
import Game.GameObject;
import org.omg.CORBA.PRIVATE_MEMBER;

public class MapPanelView extends JPanel implements Observer {
    MapPanelModel model;

    public MapPanelView(MapPanelModel model) {
        this.model = model;
        model.addObserver(this);

        setBackground(Color.black);
        setPreferredSize(new Dimension(model.getWidth() * 16 + 100, model.getHeight() * 16 + 100));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        EditorProperties.currentMap = model.map;

        drawTiles(g);
        drawObjects(g);
        drawSelection(g);
        drawWalkable(g);
        drawWeather(g);
        drawEvents(g);
        if (EditorModel.getSelf().getGridDisplay())
            drawGrid(g);
        drawPlayer(g);
        drawDroppable(g);
        drawBorders(g);
        drawCoordinates(g);
    }

    private void drawTiles(Graphics g) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                String path = model.map.getPathTile(j, i);
                BufferedImage image = SpriteResources.pathToImage.get(path);
                if (image == null)
                    SpriteResources.addImage(path);
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
            if (image == null)
                SpriteResources.addImage(path);
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

    private void drawSelection(Graphics g) {
        if (EditorProperties.selection != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setColor(new Color(255, 255, 224, 100));
            g2.fill(EditorProperties.selection);
        }
    }

    private void drawWalkable(Graphics g) {
        int x = 0;
        int y = 0;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(255, 0, 0, 100));

        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                if (!model.map.isWalkable(j, i))
                    g2.fill(new Rectangle(x, y, 16, 16));
                x += 16;
            }
            x = 0;
            y += 16;
        }
    }

    private void drawWeather(Graphics g) {
        int x = 0;
        int y = 0;

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 0, 255, 100));

        for (int i = 0; i < model.getHeight(); i++) {
            for (int j = 0; j < model.getWidth(); j++) {
                if (model.map.getPathWeatherTile(j, i) != null)
                    g2.fill(new Rectangle(x, y, 16, 16));
                x += 16;
            }
            x = 0;
            y += 16;
        }
    }

    private void drawEvents(Graphics g) {
        for (GameObject obj : model.map.getEvents()) {
            String path = obj.get_sprite();
            BufferedImage image = SpriteResources.pathToImage.get(path);
            if (image == null)
                SpriteResources.addImage(path);
            g.drawImage(image, obj.getX() * 16, obj.getY() * 16, null);
        }
    }

    private void drawPlayer(Graphics g) {
        String path = "resources/player/player.png";
        BufferedImage img = SpriteResources.pathToImage.get(path);
        if (img == null) {
            SpriteResources.addImage(path);
            img = SpriteResources.pathToImage.get(path);
        }
        if (EditorProperties.playerPosition != null && EditorProperties.playerMapId == EditorProperties.currentMap.getId())
            g.drawImage(img, EditorProperties.playerPosition.x * 16, EditorProperties.playerPosition.y * 16, null);
    }

    private void drawDroppable(Graphics g) {
        Point p = model.getMouseOnPanel();
        if (p != null && SpriteResources.foregroundSprites) {
            BufferedImage img = SpriteResources.pathToImage.get(SpriteResources.selectedSprite);
            if (img != null) {
                    GameForeground obj = new GameForeground(p.x - img.getWidth() / 32, p.y - img.getHeight() / 32, SpriteResources.selectedSprite);
                    BufferedImage nimg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g2 = nimg.createGraphics();
                    g2.drawImage(img, 0,0, null);
                    g2.setComposite(AlphaComposite.SrcAtop);
                    if (model.canAddObject(EditorProperties.currentMap, obj))
                        g2.setColor(new Color(0,255,0,170));
                    else
                        g2.setColor(new Color(255,0,0,170));
                    g2.fillRect(0,0,img.getWidth(),img.getHeight());
                    g2.dispose();
                    g.drawImage(nimg, obj.getX() * 16, obj.getY() * 16, null);
            }
        }
    }

    private void drawBorders(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 0, 0, 255));
        int dx = model.getWidth() * 16;
        int dy = model.getHeight() * 16;
        g2.fill(new Rectangle(dx, 0, 1000, dy + 1000));
        g2.fill(new Rectangle(0, dy, dx + 1000, 1000));
    }

    private void drawCoordinates(Graphics g) {
        Point p = model.getMouseOnPanel();
        if (p == null)
            return;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(255, 255, 255, 255));
        g2.drawString("(" + p.getX() + ", " + p.getY() + ")", getParent().getWidth() - 80, getParent().getHeight() - 12);
    }

    @Override
    public void update(String str) {
        if (str.equals("repaint")) {
            revalidate();
            repaint();
        }
    }
}
