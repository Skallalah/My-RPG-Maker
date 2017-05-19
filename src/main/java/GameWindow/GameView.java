package GameWindow;

import Common.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameView extends JFrame implements Observer{
    public GameView(GameModel model) {
        gameModel_ = model;
        setTitle(model.getWorld().getName());
        setSize(500, 400);
        gameMapViewer_ = new GameMapViewer(model);
        JPanel map_panel = gameMapViewer_;
        add(map_panel);

        map_panel.repaint();
        setVisible(true);
        model.addObserver(this);
    }

    private GameMapViewer gameMapViewer_;
    private GameModel gameModel_;

    @Override
    public void update(String str) {
    }
}
