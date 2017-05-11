package GameWindow;

import Common.Executor;
import Common.SpriteResources;
import Game.GameMap;
import Game.GameWorld;

import java.awt.event.KeyEvent;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameModel {
    public GameModel(GameWorld world) {
        world_ = world;
        current_map_ = world.getCharacter().get_map(world_);
    }

    public GameWorld getWorld() {
        return world_;
    }

    public GameMap getCurrent_map() {
        return current_map_;
    }

    public void move_character(KeyEvent keyPressed) {
            System.out.println(keyPressed.getKeyCode());
            if (keyPressed.getKeyCode() == 38) {
                world_.getCharacter().moveUp(world_);
            }
            else if (keyPressed.getKeyCode() == 39) {
                world_.getCharacter().moveRight(world_);
            }
            else if (keyPressed.getKeyCode() == 37) {
                world_.getCharacter().moveLeft(world_);
            }
            else {
                world_.getCharacter().moveDown(world_);
            }
            System.out.println(world_.getCharacter().getX());
    }

    private GameWorld world_;
    private GameMap current_map_;
}
