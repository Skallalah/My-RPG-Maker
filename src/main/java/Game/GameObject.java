package Game;

/**
 * Created by skallalah on 06/05/17.
 */
public class GameObject {
    public GameObject(Integer pos_x, Integer pos_y, Integer map_id) {
        id_map_ = map_id;
        pos_x_ = pos_x;
        pos_y_ = pos_y;
        img_path_sprite_ = null;
        action_button_ = null;
        player_touch_ = null;
        id_ = null;
    }

    public void setId(Integer id) {
        this.id_ = id_;
    }

    public Integer getId() {
        return id_;
    }

    public int getX() {
        return pos_x_;
    }

    public int getY() {
        return pos_y_;
    }

    public String get_sprite() {
        return img_path_sprite_;
    }

    public void set_sprite(String sprite) {
        this.img_path_sprite_ = sprite;
    }


    // Script getter/setter
    public GameScript get_interaction() {
        return action_button_;
    }

    public GameScript get_touch() {
        return player_touch_;
    }

    public void set_interaction(GameScript script) {
        this.action_button_ = script;
    }

    public void set_touch(GameScript script) {
        this.player_touch_ = script;
    }

    // Id for better use inside the
    private Integer id_;

    // Localisation of the object
    private Integer pos_x_;
    private Integer pos_y_;
    private Integer id_map_;

    // Script utilities
    private String img_path_sprite_;
    private GameScript action_button_; // Id for the script when a player push the interaction button
    private GameScript player_touch_; // Id for the script when a player touch the position of the object

}
