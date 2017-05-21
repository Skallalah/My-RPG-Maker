package Game;

import Common.SpriteResources;

import java.awt.image.BufferedImage;
import java.util.Vector;

/**
 * Created by skallalah on 05/05/17.
 */
public class GameMap {
    public GameMap(String name, int width, int height, String path_basic_tile) {
        name_ = name;
        objects_ = new Vector<>();
        events_ = new Vector<>();
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
        id_ = null;
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

    public Integer getId() {
        return id_;
    }

    public void setId(Integer id) {
        this.id_ = id;
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

    public String getPathWeatherTile(int x, int y) {
        return map_.get(y).get(x).getPath_weather();
    }

    public void setPathWeatherTile(int x, int y, String path) {
        map_.get(y).get(x).setPath_weather(path);
    }

    public Vector<GameForeground> getObjects() {
        return objects_;
    }

    public Vector<GameObject> getEvents() {
        return events_;
    }

    // Script add

    public void add_script(GameObject object) {
        events_.add(object);
    }

    public GameObject has_script(int x, int y) {
        /*if (((x < 0) || (x >= width_)) || ((y < 0) || (y >= height_))) {
            return null;
        }*/
        for (GameObject g : events_) {
            String path = g.get_sprite();
            BufferedImage img = SpriteResources.pathToImage.get(path);
            if (img == null)
                SpriteResources.addImage(path);
            else {
                int x_ = g.getX();
                int y_ = g.getY();
                int dx = (img.getWidth() / 16) - 1;
                int dy = (img.getHeight() / 16) - 1;
                if (dx <= 0) {
                    dx = 0;
                }
                if (dy <= 0) {
                    dy = 0;
                }
                if (((x >= x_) && (x <= x_ + dx)) && ((y >= y_) && (y <= y_ + dy))) {
                    return g;
                }
            }
        }
        return null;
    }

    private Integer id_;
    private String name_;
    private int width_;
    private int height_;
    private Vector<GameForeground> objects_;
    private Vector<Vector<Tile>> map_;
    private Vector<GameObject> events_;
}
