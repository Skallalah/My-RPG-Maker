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
        variables_ = new Hashtable<>();
        name_ = name;
        id_map_ = 0;
        id_variable_ = 0;
        time_cycle_ = null;
        current_dialogue_ = null;
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

    //Time cycle manipulation
    public Integer getTime_cycle() {
        return time_cycle_;
    }

    public void setTime_cycle(Integer time) {
        this.time_cycle_ = time;
    }

    //Dialogue Manipulation
    public boolean in_dialogue() {
        return (current_dialogue_ != null);
    }

    public void end_dialogue()  {
        current_dialogue_ = null;
    }

    public String getCurrent_dialogue() {
        return current_dialogue_;
    }

    public void add_dialogue(String dialogue) {
        current_dialogue_ = dialogue;
    }

    //Variable manipulation (by id)
    public GameVariable getVariable(Integer id) {
        return variables_.get(id);
    }

    public Hashtable<Integer, GameVariable> getVariables() {
        return variables_;
    }

    public Integer addVariable(GameVariable var) {
        var.setId(id_variable_);
        variables_.put(id_variable_++, var);
        return id_variable_;
    }

    public void deleteVariable(Integer id) {
        variables_.remove(id);
    }

    // Attributes
    private String name_;

    private GameCharacter character_;

    private Integer id_map_;
    private Integer id_variable_;

    private Hashtable<Integer, GameMap> maps_;
    private Hashtable<Integer, GameVariable> variables_;
    private Integer time_cycle_;
    private String current_dialogue_;

}
