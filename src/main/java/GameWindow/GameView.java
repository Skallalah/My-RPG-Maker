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
        gameMapViewer_ = new GameMapViewer(model);
        JPanel map_panel = gameMapViewer_;
        add(map_panel);

        map_panel.repaint();
        setVisible(true);
    }

    public void paintView() {
        gameMapViewer_.repaint();
    }

    private GameMapViewer gameMapViewer_;
    private GameModel gameModel_;

}
