package Game;

import Game.GameMap;
import Game.GameScript;

import java.util.Hashtable;

/**
 * Created by skallalah on 05/05/17.
 */
public class GameWorld {
    public GameWorld(String name) {
        maps_ = new Hashtable<>();
        scripts_ = new Hashtable<>();
        name_ = name;
        id_map_ = 0;
        id_script_ = 0;
        id_characters_ = 0;
    }

    public String getName() {
        return name_;
    }

    //Map manipulation (by id)
    public GameMap getMap(Integer id) {
        return maps_.get(id);
    }

    public Integer addMap(GameMap map) {
        maps_.put(id_map_++, map);
        return id_map_;
    }

    public void deleteMap(Integer id) {
        maps_.remove(id);
    }

    //Script manipulation (by id)
    public GameScript getScript(Integer id) {
        return scripts_.get(id);
    }

    public Integer addScript(GameScript script) {
        scripts_.put(id_script_++, script);
        return id_script_;
    }

    public void deleteScript(Integer id) {
        scripts_.remove(id);
    }

    private String name_;

    private Integer id_map_;
    private Integer id_script_;
    private Integer id_characters_;

    private Hashtable<Integer, GameMap> maps_;
    private Hashtable<Integer, GameScript> scripts_;

}
