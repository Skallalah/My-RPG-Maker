package Game;

import Game.CharacterPersonalization.GameClass;

/**
 * Created by skallalah on 06/05/17.
 */
public class GameCharacter {

    public GameCharacter(String name, String sprite, GameClass gameclass) {
        name_ = name;
        path_img_sprite_ = sprite;
        class_ = gameclass;
        level_ = 1;
        state_ = 1;
        cur_direction_ = direction.DOWN;
    }

    public enum direction {
        UP (3),
        LEFT (1),
        RIGHT (2),
        DOWN (0);

        private int value_;


        direction(int i) {
            value_ = i;
        }

        public int getValue() {
            return value_;
        }
    }

    public void spawn_player(Integer map_id, Integer x, Integer y) {
        pos_x_ = x;
        pos_y_ = y;
        id_map_ = map_id;
    }

    // Move (for easier modification)
    public boolean moveUp(GameWorld world) {
        if (pos_y_ <= 0)
            return false;
        if (world.getMap(id_map_).isWalkable(pos_x_, pos_y_ - 1)) {
            pos_y_--;
            return true;
        }
        return false;
    }

    public boolean moveDown(GameWorld world) {
        if (pos_y_ >= world.getMap(id_map_).getHeight())
            return false;
        if (world.getMap(id_map_).isWalkable(pos_x_,pos_y_ + 1)) {
            pos_y_++;
            return true;
        }
        return false;
    }

    public boolean moveLeft(GameWorld world) {
        if (pos_x_ <= 0)
            return false;
        if (world.getMap(id_map_).isWalkable(pos_x_ - 1, pos_y_)) {
            pos_x_--;
            return true;
        }
        return false;
    }

    public boolean moveRight(GameWorld world) {
        if (pos_x_ >= world.getMap(id_map_).getWidth())
            return false;
        if (world.getMap(id_map_).isWalkable(pos_x_ + 1, pos_y_)) {
            pos_x_++;
            return true;
        }
        return false;
    }


    public void next_state() {
        state_ = (state_ + 1) % 3;
    }

    public void idle_state() {
        state_ = 1;
    }

    public Integer getState() {
        return state_;
    }

    public void setCur_direction_(direction direction) {
        this.cur_direction_ = direction;
    }

    public direction getCur_direction() {
        return cur_direction_;
    }

    // Getter and setter
    public String getName() {
        return name_;
    }

    public GameMap get_map(GameWorld world) {
        return world.getMap(id_map_);
    }

    public void setId_map_(Integer id_map) {
        this.id_map_ = id_map;
    }

    public Integer getX() {
        return pos_x_;
    }

    public Integer getY() {
        return pos_y_;
    }

    public void setX(Integer x) {
        this.pos_x_ = x;
    }

    public void setY(Integer y) {
        this.pos_y_ = y;
    }

    public String get_sprite() {
        return path_img_sprite_;
    }

    public void set_sprite(String sprite) {
        this.path_img_sprite_ = sprite;
    }

    // Characteristics and personalization


    private String name_;
    private String path_img_sprite_;
    private Integer id_map_;
    private Integer pos_x_;
    private Integer pos_y_;

    //Character information for movement
    private Integer state_;
    private direction cur_direction_;

    // Character information (class, stats, etc...)
    private Integer level_;
    private GameClass class_;
}
