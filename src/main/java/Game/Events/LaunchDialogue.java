package Game.Events;

import Game.GameDialogue;
import Game.GameScript;
import Game.GameWorld;

/**
 * Created by skallalah on 09/05/17.
 */
public class LaunchDialogue implements GameScript {
    public LaunchDialogue() {
        id_dialogue_ = null;
    }

    @Override
    public boolean execute(GameWorld world) {
        // Execute dialogue inside panel
        return true;
    }

    private Integer id_dialogue_;
}
