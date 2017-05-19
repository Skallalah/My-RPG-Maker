package SpritesPanel;

import Common.Observer;
import Common.SpriteResources;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.lang.Integer.max;

public class SpritesPanelView extends JPanel implements Observer {
    SpritesPanelModel model;

    public SpritesPanelView(SpritesPanelModel model){
        this.model = model;
        model.addObserver(this);

        setBackground(Color.WHITE);
        setDoubleBuffered(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        int maxy = 0;
        for (BufferedImage img : model.getImages()) {
            if (x + img.getWidth() > getWidth()) {
                x = 0;
                y = maxy + 16;
            }
            g.drawImage(img, x, y, null);
            x += img.getWidth() + 16;
            maxy = max(maxy, y + img.getHeight());
        }
        setPreferredSize(new Dimension(0, maxy + 100));
    }

    private void errorOnSprite() {
        JOptionPane.showMessageDialog(this,
                "This is not a valid sprite or the dimension of the choosen sprite might not be multiple of 16.",
                "Error on sprite",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void update(String str) {
        if (str.equals("repaint")) {
            revalidate();
            repaint();
        }
        else if (str.equals("errorOnSprite")) {
            errorOnSprite();
        }
    }
}