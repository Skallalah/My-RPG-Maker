package Game.ScriptBlocks;

import Game.GameScript;
import Game.GameVariable;
import Game.GameWorld;

/**
 * Created by skallalah on 09/05/17.
 */
public class CheckBlock implements GameScript {
    public CheckBlock() {
        var_id_ = null;
        comp_ = null;
        value_ = null;
    }

    public enum compType {
        EQUAL,
        LESS,
        MORE
    }

    @Override
    public boolean execute(GameWorld world) {
        switch (comp_) {
            case EQUAL:
                return (world.getVariable(var_id_).getValue().equals(value_));
            case LESS:
                return (world.getVariable(var_id_).getValue() < value_);
            case MORE:
                return (world.getVariable(var_id_).getValue() > value_);
            default:
                return false;
        }
    }

    public void setComp(compType comp) {
        this.comp_ = comp;
    }

    public void setValue(Integer value) {
        this.value_ = value;
    }

    public void setVar_id(Integer var_id) {
        this.var_id_ = var_id;
    }

    private Integer var_id_;
    private compType comp_;
    private Integer value_;
}
