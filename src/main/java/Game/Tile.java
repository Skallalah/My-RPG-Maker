package Game;

/**
 * Created by skallalah on 05/05/17.
 */
public class Tile {
    public Tile(String path) {
        walkable_ = true;
        path_img_ = path;
    }

    public boolean isWalkable() {
        return walkable_;
    }

    public void setWalkable(boolean walkable) {
        walkable_ = walkable;
    }

    public void setPath_img(String path) {
        path_img_ = path;
    }

    public String getPath_img() {
        return path_img_;
    }

    public void setPath_weather(String path) {
        path_weather_ = path;
    }

    public String getPath_weather() {
        return path_weather_;
    }

    private boolean walkable_;
    private String path_img_;
    private String path_weather_;
}
