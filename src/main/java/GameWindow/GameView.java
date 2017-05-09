package GameWindow;

import javax.swing.*;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameView extends JFrame {
    public GameView(GameModel model) {
        gameModel_ = model;
        setTitle(model.getWorld().getName());
        setSize(500, 400);
        JPanel map_panel = new GameMapViewer(model);
        add(map_panel);
        map_panel.repaint();
        setVisible(true);
    }

    private GameModel gameModel_;

}
