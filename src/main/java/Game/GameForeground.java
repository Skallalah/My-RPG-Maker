package Game;

/**
 * Created by skallalah on 05/05/17.
 */
public class GameForeground {
    public GameForeground(int pos_x, int pos_y, String path) {
        x_ = pos_x;
        y_ = pos_y;
        path_img_ = path;
    }

    public int getX() {
        return x_;
    }

    public int getY() {
        return y_;
    }

    public String getPath() {
        return path_img_;
    }

    private int x_;
    private int y_;
    private String path_img_;
}
