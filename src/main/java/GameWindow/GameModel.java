package GameWindow;

import Game.GameMap;
import Game.GameWorld;

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

    private GameWorld world_;
    private GameMap current_map_;
}
