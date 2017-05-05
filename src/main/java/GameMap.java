import java.util.Vector;

/**
 * Created by skallalah on 05/05/17.
 */
public class GameMap {
    public GameMap(String name, int width, int height, String path_basic_tile) {
        name_ = name;
        objects_ = new Vector<>();
        width_ = width;
        height_ = height;
        map_ = new Vector<>();
        for (int h = 0; h < height_; h++) {
            Vector<Tile> row = new Vector<>();
            for (int w = 0; w < width_; w++) {
                Tile tile = new Tile(path_basic_tile);
                row.add(tile);
            }
            map_.add(row);
        }
    }

    public int getHeight() {
        return height_;
    }

    public int getWidth() {
        return width_;
    }

    public GameObject getObject(int index) {
        return objects_.get(index);
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    public boolean isWalkable(int x, int y) {
        return map_.get(y).get(x).isWalkable();
    }

    public boolean setWalkable(int x, int y, boolean walkable) {
        map_.get(y).get(x).setWalkable(walkable);
    }

    private String name_;
    private int width_;
    private int height_;
    private Vector<GameObject> objects_;
    private Vector<Vector<Tile>> map_;

}
