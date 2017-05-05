import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by skallalah on 05/05/17.
 */
public class World {
    public World(String name) {
        maps_ = new Vector<GameMap>();

        name_ = name;
    }

    public String getName() {
        return name_;
    }

    public GameMap getMap(int index) {
        return maps_.get(index);
    }

    public void addMap(GameMap map) {
        maps_.add(map);
    }



    private String name_;
    private Vector<GameMap> maps_;

}
