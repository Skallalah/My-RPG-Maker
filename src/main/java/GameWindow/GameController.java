package GameWindow;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameController {
    public GameController(GameModel model, GameController controller) {
        gameController_ = controller;
        gameModel_ = model;
    }

    public void control() {

    }

    private GameModel gameModel_;
    private GameController gameController_;
}
