package Game;

import Game.GameMap;
import Game.GameScript;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by skallalah on 05/05/17.
 */
public class GameWorld {
    public GameWorld(String name) {
        maps_ = new Hashtable<>();
        scripts_ = new Hashtable<>();
        objects_ =  new Hashtable<>();
        variables_ = new Hashtable<>();
        name_ = name;
        id_map_ = 0;
        id_script_ = 0;
        id_objects_ = 0;
        id_variables_ = 0;
    }

    public String getName() {
        return name_;
    }

    public GameCharacter getCharacter() {
        return character_;
    }

    public void setCharacter_(GameCharacter character) {
        this.character_ = character;
    }

    //Map manipulation (by id)
    public GameMap getMap(Integer id) {
        return maps_.get(id);
    }

    public Hashtable<Integer, GameMap> getMaps() {
        return maps_;
    }

    public Integer addMap(GameMap map) {
        map.setId(id_map_);
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
        script.setId(id_script_);
        scripts_.put(id_script_++, script);
        return id_script_;
    }

    public void deleteScript(Integer id) {
        scripts_.remove(id);
    }

    //Object manipulation (by id)
    public GameObject getObject(Integer id) {
        return objects_.get(id);
    }

    public Integer addObject(GameObject object) {
        object.setId(id_objects_);
        objects_.put(id_objects_++, object);
        return id_objects_;
    }

    public void deleteObject(Integer id) {
        objects_.remove(id);
    }

    //Variable manipulation (by id)
    public GameVariable getVariable(Integer id) {
        return variables_.get(id);
    }

    public Integer addVariable(GameVariable variable) {
        variable.setId(id_variables_);
        variables_.put(id_variables_++, variable);
        return id_variables_;
    }

    public void deleteVariable(Integer id) {
        variables_.remove(id);
    }

    private String name_;

    private GameCharacter character_;

    private Integer id_map_;
    private Integer id_script_;
    private Integer id_objects_;
    private Integer id_variables_;

    private Hashtable<Integer, GameMap> maps_;
    private Hashtable<Integer, GameScript> scripts_;
    private Hashtable<Integer, GameObject> objects_;
    private Hashtable<Integer, GameVariable> variables_;

}
