package Game.Events;

import Game.GameScript;
import Game.GameWorld;

/**
 * Created by colin_l on 5/14/17.
 */
public class TeleportPlayer implements GameScript {
    public TeleportPlayer(Integer mapId, Integer id, Integer x, Integer y) {
        map_id_ = mapId;
        id_ = id;
        x_ = x;
        y_ = y;
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
        if (world.getMap(map_id_) == null) {
            return false;
        }
        if (x_ > world.getMap(map_id_).getWidth()) {
            return false;
        }
        if (y_ > world.getMap(map_id_).getHeight()) {
            return false;
        }
        int x = x_;
        int y = y_;
        if (x_ < 0) {
            x = 0;
        }
        if (y_ < 0) {
            y = 0;
        }
        world.getCharacter().setX(x);
        world.getCharacter().setY(y);
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
