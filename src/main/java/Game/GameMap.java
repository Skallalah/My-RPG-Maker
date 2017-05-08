package Game;

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

    public GameForeground getObject(int index) {
        return objects_.get(index);
    }

    public void addObject(GameForeground object) {
        objects_.add(object);
    }

    public String getName() {
        return name_;
    }

    public void setName(String name) {
        this.name_ = name;
    }

    // Map modification of tiles
    public boolean isWalkable(int x, int y) {
        return map_.get(y).get(x).isWalkable();
    }

    public void setWalkable(int x, int y, boolean walkable) {
        map_.get(y).get(x).setWalkable(walkable);
    }

    public void setPathTile(int x, int y, String path) {
        map_.get(y).get(x).setPath_img(path);
    }

    public String getPathTile(int x, int y) {
        return map_.get(y).get(x).getPath_img();
    }

    public Vector<GameForeground> getObjects() {
        return objects_;
    }

    private String name_;
    private int width_;
    private int height_;
    private Vector<GameForeground> objects_;
    private Vector<Vector<Tile>> map_;

}
