package Game.CharacterPersonalization;

import java.util.Vector;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameClass {
    public GameClass(String name) {
        name_ = name;
        prof_bonus_ = new Vector<>();
        Integer bonus = 2;
        for (int i = 1; i <= 20; i++) {
            //System.out.println("Level: " + i + " | " + bonus);
            prof_bonus_.add(bonus);
            if (i % 4 == 0) {
                bonus++;
            }
        }
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    private String name_;
    private Vector<Integer> prof_bonus_;
}
