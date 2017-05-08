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
        img_path_face_ = null;
        action_button_ = null;
        player_touch_ = null;
    }

    // Image modifications
    public String get_face() {
        return img_path_face_;
    }

    public void set_face(String face) {
        this.img_path_face_ = face;
    }

    public String get_sprite() {
        return img_path_sprite_;
    }

    public void set_sprite(String sprite) {
        this.img_path_sprite_ = sprite;
    }


    // Script getter/setter
    public Integer get_interaction() {
        return action_button_;
    }

    public Integer get_touch() {
        return player_touch_;
    }

    public void set_interaction(Integer id_script) {
        this.action_button_ = id_script;
    }

    public void set_touch(Integer id_script) {
        this.player_touch_ = id_script;
    }

    // Localisation of the object
    private Integer pos_x_;
    private Integer pos_y_;
    private Integer id_map_;

    // Script utilities
    private String img_path_sprite_;
    private String img_path_face_; // For dialogues
    private Integer action_button_; // Id for the script when a player push the interaction button
    private Integer player_touch_; // Id for the script when a player touch the position of the object

}
