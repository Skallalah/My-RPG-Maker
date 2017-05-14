package Game.Events;

import Game.GameScript;
import Game.GameWorld;

/**
 * Created by colin_l on 5/14/17.
 */
public class TeleportPlayer implements GameScript {
    public TeleportPlayer() {
        map_id_ = null;
        id_ = null;
        x_ = null;
        y_ = null;
    }

    public void setX(Integer x) {
        this.x_ = x;
    }

    public void setY(Integer y) {
        this.y_ = y;
    }

    public void setMap_id(Integer map_id) {
        this.map_id_ = map_id;
    }

    @Override
    public boolean execute(GameWorld world) {
        world.getCharacter().setX(x_);
        world.getCharacter().setX(y_);
        world.getCharacter().setId_map_(map_id_);
        return true;
    }

    @Override
    public Integer getId() {
        return id_;
    }

    @Override
    public void setId(Integer id) {
        id_ = id;
    }

    private Integer id_;
    private Integer x_;
    private Integer y_;
    private Integer map_id_;
}
