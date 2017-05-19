package MapPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Common.*;
import Common.Event;
import EditorWindow.EditorModel;
import EditorWindow.Walkable;
import Game.GameForeground;
import Game.GameMap;

public class MapPanelModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    public GameMap map;
    Point mouseOnPanel;

    public MapPanelModel(GameMap map) {
        this.map = map;
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }

    public void setMouseOnPanel(Point p) {
        mouseOnPanel = p;
    }

    public Point getMouseOnPanel() {
        return mouseOnPanel;
    }

    public void drop(int x, int y) {
        BufferedImage img = SpriteResources.pathToImage.get(SpriteResources.selectedSprite);
        if (img != null) {
            if (SpriteResources.foregroundSprites) {
                GameForeground obj = new GameForeground(x - img.getWidth() / 32, y - img.getHeight() / 32, SpriteResources.selectedSprite);
                if (canAddObject(map, obj)) {
                    map.addObject(obj);
                    History.addEvent(new Event(Event.Action.ADD_OBJECT, map, obj));
                }
            }
            else if (tileDropCondition(x, y))
            {
                History.addEvent(new Event(Event.Action.ADD_TILE, map, x, y));
                map.setPathTile(x, y, SpriteResources.selectedSprite);
                map.setPathWeatherTile(x, y, EditorProperties.weatherTile);
                if (EditorProperties.walkable == Walkable.WALKABLE)
                    map.setWalkable(x, y, true);
                else if (EditorProperties.walkable == Walkable.NON_WALKABLE)
                    map.setWalkable(x, y, false);
            }
        }
        notifyObserver("repaint");
    }

    private boolean tileDropCondition(int x, int y) {
        return (!map.getPathTile(x, y).equals(SpriteResources.selectedSprite))
                || (EditorProperties.weatherTile != null && map.getPathWeatherTile(x, y) == null)
                || (map.getPathWeatherTile(x, y) != null && !map.getPathWeatherTile(x, y).equals(EditorProperties.weatherTile))
                || (map.isWalkable(x, y) && EditorProperties.walkable == Walkable.NON_WALKABLE)
                || (!map.isWalkable(x, y) && EditorProperties.walkable != Walkable.NON_WALKABLE);
    }

    public boolean canAddObject(GameMap map, GameForeground newObj) {

        int nx = newObj.getX();
        int ny = newObj.getY();
        BufferedImage img = SpriteResources.pathToImage.get(newObj.getPath());
        int ndx = (img.getWidth() / 16) - 1;
        int ndy = (img.getHeight() / 16) - 1;

        if (nx < 0 || nx + 1 > map.getWidth() || ny < 0 || ny + 1 > map.getHeight())
            return false;

        for (GameForeground obj : map.getObjects()) {
            int x = obj.getX();
            int y = obj.getY();
            img = SpriteResources.pathToImage.get(obj.getPath());
            int dx = (img.getWidth() / 16) - 1;
            int dy = (img.getHeight() / 16) - 1;
            if (dx <= 0) {
                dx = 1;
            }
            if (dy <= 0) {
                dy = 1;
            }

            if (((nx >= x && nx <= x + dx) || (nx + ndx >= x && nx + ndx <= x + dx)) && ((ny >= y && ny <= y + dy) || (ny + ndy >= y && ny + ndy <= y + dy)))
                return false;
        }
        return true;
    }

    public void removeObjects(int x, int y) {
        Vector<GameForeground> objects = map.getObjects();
        for (GameForeground obj : objects) {

            String path = obj.getPath();
            BufferedImage img = SpriteResources.pathToImage.get(path);

            int ox = obj.getX();
            int oy = obj.getY();
            int dx = img.getWidth() / 16;
            int dy = img.getHeight() / 16;

            if (x >= ox && x <= ox + dx && y >= oy && y <= oy + dy) {
                objects.remove(obj);
                History.addEvent(new Event(Event.Action.RM_OBJECT, map, obj));
            }
        }
        notifyObserver("repaint");
    }

    public void grab(int x, int y) {
        for (GameForeground obj : map.getObjects()) {
            int ox = obj.getX();
            int oy = obj.getY();
            BufferedImage img = SpriteResources.pathToImage.get(obj.getPath());
            int odx = img.getWidth() / 16;
            int ody = img.getHeight() / 16;

            if (x >= ox && x <= ox + odx && y >= oy && y <= oy + ody) {
                SpriteResources.selectedSprite = obj.getPath();
                SpriteResources.foregroundSprites = true;
                map.getObjects().remove(obj);
                EditorModel.getSelf().notifyObserver("setCursor");
                notifyObserver("repaint");
                return;
            }
        }
        SpriteResources.selectedSprite = map.getPathTile(x, y);
        SpriteResources.foregroundSprites = false;
        EditorModel.getSelf().notifyObserver("setCursor");
    }

    public static void fillSelectionWithProperties(boolean property) {
        if (EditorProperties.selection != null) {
            Rectangle r = EditorProperties.selection;
            int x = r.x / 16;
            int y = r.y / 16;
            int w = (r.width / 16) + x;
            int h = (r.height / 16) + y;
            for (int j = y; j < h; j++) {
                for (int i = x; i < w; i++) {
                    History.addEvent(new Common.Event(Common.Event.Action.ADD_TILE, EditorProperties.currentMap, i, j));
                    if (property)
                        EditorProperties.currentMap.setWalkable(i, j, EditorProperties.walkable == Walkable.WALKABLE);
                    else
                        EditorProperties.currentMap.setPathWeatherTile(i, j, EditorProperties.weatherTile);
                }
            }
            EditorProperties.selection = null;
            SpriteResources.selectedSprite = null;
            EditorModel.getSelf().notifyObserver("repaint");
        }
    }

    public void select(int x, int y) {
        EditorProperties.x = x * 16;
        EditorProperties.y = y * 16;
        EditorProperties.selection = new Rectangle();
    }

    public void release(int ox, int oy) {
        int x = EditorProperties.x;
        int y = EditorProperties.y;
        ox *= 16;
        oy *= 16;
        if (ox < x) {
            int tmp = x;
            x = ox;
            ox = tmp;
        }
        if (oy < y) {
            int tmp = y;
            y = oy;
            oy = tmp;
        }
        EditorProperties.selection.setBounds(x, y, ox - x, oy - y);
        notifyObserver("repaint");
    }

    public void repaint() {
        notifyObserver("repaint");
    }

    @Override
    public void addObserver(Observer obs) {
        observerList.add(obs);
    }

    @Override
    public void notifyObserver(String str) {
        for(Observer obs : observerList)
            obs.update(str);
    }

    @Override
    public void removeObserver() {
        observerList = new ArrayList<>();
    }
}
