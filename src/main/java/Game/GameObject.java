package Game;

/**
 * Created by skallalah on 05/05/17.
 */
public class GameObject {
    public GameObject(int pos_x, int pos_y, String path) {
        x_ = pos_x;
        y_ = pos_y;
        path_img_ = path;
    }

    private int x_;
    private int y_;
    private String path_img_;
}
