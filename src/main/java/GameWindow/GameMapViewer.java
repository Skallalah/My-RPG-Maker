package GameWindow;


import Common.Observer;
import Common.SpriteResources;
import Game.GameForeground;
import Game.GameObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameMapViewer extends JPanel implements Observer, ActionListener {
    public GameMapViewer(GameModel model) {
        gameModel_ = model;
        model.addObserver(this);

        setBackground(Color.black);
        setPreferredSize(new Dimension(gameModel_.getCurrent_map().getWidth() * 16,
                gameModel_.getCurrent_map().getHeight() * 16));

        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.translate((this.getSize().width/2 - gameModel_.getWorld().getCharacter().getX()*16),
                (this.getSize().height/2 - gameModel_.getWorld().getCharacter().getY()*16));
        drawTiles(g);
        drawCharacter(g);
        drawObjects(g);
        drawEvents(g);
        drawWeather(g);
        drawNight(g);
        drawBorders(g);
        if (gameModel_.getWorld().in_dialogue()) {
            g.translate(-(this.getSize().width / 2 - gameModel_.getWorld().getCharacter().getX() * 16),
                    -(this.getSize().height / 2 - gameModel_.getWorld().getCharacter().getY() * 16));
            paintDialogue(g, "d");
        }
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
        Shape oldClip = g.getClip ();
        int x = gameModel_.getWorld().getCharacter().getX()*16;
        int y = gameModel_.getWorld().getCharacter().getY()*16;
        g.setClip (x, y, 16, 16);
        String path = gameModel_.getWorld().getCharacter().get_sprite();
        BufferedImage image = SpriteResources.pathToImage.get(path);
        if (image == null)
            image = loadImage(path);

        int ac_state = gameModel_.getWorld().getCharacter().getState() * 16;
        int ac_look = gameModel_.getWorld().getCharacter().getCur_direction().getValue() * 16;
        g.drawImage(image, x - ac_state, y - ac_look, 48, 64, null);
        g.setClip(oldClip);
    }

    private void drawNight(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        long counter = System.currentTimeMillis() % 30000;
        int c = (int)counter / 75;
        if (c > 200) {
            c = 200 - (c - 200);
        }

        g2.setColor(new Color(0, 0, 0, c));
        int x = gameModel_.getWorld().getCharacter().getX() * 16;
        int y = gameModel_.getWorld().getCharacter().getY() * 16;
        g2.fill(new Rectangle(-5000 + x, -5000 + y, 10000, 10000));
    }

    private void drawWeather(Graphics g) {
        int x = gameModel_.getWorld().getCharacter().getX();
        int y = gameModel_.getWorld().getCharacter().getY();

        String path = gameModel_.getCurrent_map().getPathWeatherTile(x, y);
        BufferedImage image = SpriteResources.pathToImage.get(path);
        if (image == null)
            image = loadImage(path);

        x *= 16;
        y *= 16;
        long counter = System.currentTimeMillis() % 3000;
        counter /= 10;
        int c = (int)counter;
        g.drawImage(image, -c + x - 300, c - 903 + y, null);
        g.drawImage(image, -c + x - 300, c + y - 300, null);
        g.drawImage(image, -c + x - 300, c + 303 + y, null);
        g.drawImage(image, -c + x - 1100, c - 903 + y, null);
        g.drawImage(image, -c + x - 1100, c + y - 300, null);
        g.drawImage(image, -c + x - 1100, c + 303 + y, null);
        g.drawImage(image, -c + x + 500, c - 903 + y, null);
        g.drawImage(image, -c + x + 500, c + y - 300, null);
        g.drawImage(image, -c + x + 500, c + 303 + y, null);
    }

    private void drawEvents(Graphics g) {
        for (GameObject obj : gameModel_.getCurrent_map().getEvents()) {
            String path = obj.get_sprite();
            BufferedImage image = SpriteResources.pathToImage.get(path);
            if (image == null)
                SpriteResources.addImage(path);
            g.drawImage(image, obj.getX() * 16, obj.getY() * 16, null);
        }
    }

    private BufferedImage loadImage(String path) {
        BufferedImage image = null;
        if (path != null) {
            try {
                image = ImageIO.read(new File(path));
                SpriteResources.pathToImage.put(path, image);
                SpriteResources.imageToPath.put(image, path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return image;
    }
    private void paintDialogue(Graphics g, String dialogue) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(53, 53, 223, 255));
        g2.fill(new Rectangle(0, this.getHeight()/2 + 200, this.getWidth(), this.getHeight()/2 + 200));
        g2.setColor(new Color(30, 34, 199, 185));
        g2.fill(new Rectangle(20, this.getHeight()/2 + 220, this.getWidth() - 40, this.getHeight()/2 + 220));
    }


    public void actionPerformed(ActionEvent ev){
        if(ev.getSource() == timer){
            repaint();
        }
    }

    private void drawBorders(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(0, 0, 0, 255));
        int dx = gameModel_.getCurrent_map().getWidth() * 16;
        int dy = gameModel_.getCurrent_map().getHeight() * 16;
        g2.fill(new Rectangle(dx, 0, 1000, dy + 1000));
        g2.fill(new Rectangle(0, dy, dx + 1000, 1000));
    }

    private GameModel gameModel_;
    Timer timer=new Timer(16, this);

    @Override
    public void update(String str) {
        if (str.equals("repaint")) {
            revalidate();
            repaint();
        }
    }
}
