package Game.Events;

import Game.GameDialogue;
import Game.GameScript;
import Game.GameWorld;

/**
 * Created by skallalah on 09/05/17.
 */
public class LaunchDialogue implements GameScript {
    public LaunchDialogue(GameDialogue dialogue) {
        dialogue_ = dialogue;
    }

    @Override
    public boolean execute(GameWorld world) {
        world.add_dialogue(dialogue_.getContent());
        return true;
    }

    public void setId(Integer id) {
        this.id_ = id_;
    }

    public Integer getId() {
        return id_;
    }

    private Integer id_;
    private GameDialogue dialogue_;
}
