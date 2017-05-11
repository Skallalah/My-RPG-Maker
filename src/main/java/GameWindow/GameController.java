package GameWindow;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameController {
    public GameController(GameModel model, GameView view) {
        gameView_ = view;
        gameModel_ = model;
    }

    public void control() {
        gameView_.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                System.out.println("Typed: " + keyEvent.getKeyCode());
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                gameModel_.move_character(keyEvent);
                gameView_.paintView();
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                System.out.println("Released: " + keyEvent.getKeyCode());
            }
        });
    }

    private GameModel gameModel_;
    private GameView gameView_;
}
