package Game.ScriptBlocks;

import Game.GameScript;
import Game.GameWorld;

/**
 * Created by skallalah on 08/05/17.
 */
public class IfBlock implements GameScript {
    public IfBlock() {
        condition_ = null;
        then_ = null;
        else_ = null;
        id_ = null;
    }

    @Override
    public boolean execute(GameWorld world) {
        if (condition_.execute(world)) {
            return then_.execute(world);
        }
        else {
            if (else_ != null) {
                return else_.execute(world);
            }
            return false;
        }
    }

    public void setCondition(GameScript condition) {
        this.condition_ = condition;
    }

    public void setElse(GameScript elseScript) {
        this.else_ = elseScript;
    }

    public void setThen(GameScript thenScript) {
        this.then_ = thenScript;
    }

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id_;
    }

    private Integer id_;
    private GameScript condition_;
    private GameScript then_;
    private GameScript else_;
}
