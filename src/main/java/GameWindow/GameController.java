package GameWindow;

import javax.sound.sampled.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

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
                if (keyEvent.getKeyCode() == 0) {
                    gameModel_.interaction();
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                System.out.println("Pressed: " + keyEvent.getKeyCode());
                if (keyEvent.getKeyCode() == 32) {
                    gameModel_.interaction();
                    return;
                } else if (keyEvent.getKeyCode() == 69){
                    gameModel_.inventory();
                }
                else {
                    gameModel_.move_character(keyEvent);
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                System.out.println("Released: " + keyEvent.getKeyCode());
                gameModel_.idle_state();
            }
        });
    }

    private GameModel gameModel_;
    private GameView gameView_;
}
