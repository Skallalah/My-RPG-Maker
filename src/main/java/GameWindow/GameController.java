package GameWindow;

import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        gameView_.getPlayButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameView_.playGame();
            }
        });

        gameView_.getCancelButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                gameView_.cancelGame();
            }
        });

        gameView_.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 0) {
                    gameModel_.interaction();
                }
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
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
                gameModel_.idle_state();
            }


        });
    }

    private GameModel gameModel_;
    private GameView gameView_;
}
