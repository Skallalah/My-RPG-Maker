package Game;

/**
 * Created by skallalah on 08/05/17.
 */
public class GameVariable {
    public GameVariable(String name, Integer value) {
        name_ = name;
        value_ = value;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public void setValue(Integer value) {
        this.value_ = value;
    }

    public String getName() {
        return name_;
    }

    public Integer getValue() {
        return value_;
    }

    private String name_;
    private Integer value_;
}
