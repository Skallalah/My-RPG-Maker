package GameWindow;


import Common.SpriteResources;
import Game.GameForeground;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameMapViewer extends JPanel {
    public GameMapViewer(GameModel model) {
        gameModel_ = model;
        setBackground(Color.black);
        setPreferredSize(new Dimension(gameModel_.getCurrent_map().getWidth() * 16,
                gameModel_.getCurrent_map().getHeight() * 16));


    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate((this.getSize().width/2 - gameModel_.getWorld().getCharacter().getX()*16),
                (this.getSize().height/2 - gameModel_.getWorld().getCharacter().getY()*16));

        drawTiles(g);
        drawCharacter(g);
        drawObjects(g);
    }

    private void drawTiles(Graphics g) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < gameModel_.getCurrent_map().getHeight(); i++) {
            for (int j = 0; j < gameModel_.getCurrent_map().getWidth(); j++) {
                String path = gameModel_.getCurrent_map().getPathTile(j, i);
                BufferedImage image = SpriteResources.pathToImage.get(path);
                g.drawImage(image, x, y, null);
                x += 16;
            }
            x = 0;
            y += 16;
        }
    }

    private void drawObjects(Graphics g) {
        for (GameForeground obj : gameModel_.getCurrent_map().getObjects()) {
            String path = obj.getPath();
            BufferedImage image = SpriteResources.pathToImage.get(path);
            g.drawImage(image, obj.getX() * 16, obj.getY() * 16, null);
        }
    }

    private void drawCharacter(Graphics g) {
        /*BufferedImage image = SpriteResources.pathToImage.get(gameModel_.getWorld().getCharacter().get_sprite());
        g.drawImage(image, gameModel_.getWorld().getCharacter().getX() * 16, gameModel_.getWorld().getCharacter().getY() * 16, null);*/
        Shape oldClip = g.getClip ();
        int x = gameModel_.getWorld().getCharacter().getX()*16;
        int y = gameModel_.getWorld().getCharacter().getY()*16;
        g.setClip (x, y, 16, 16);
        BufferedImage image = SpriteResources.pathToImage
                .get(gameModel_.getWorld().getCharacter().get_sprite());
        g.drawImage(image, x, y, 48, 64, null);
        g.setClip(oldClip);
    }

    private GameModel gameModel_;

}
