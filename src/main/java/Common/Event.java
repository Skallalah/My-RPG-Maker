package Common;

import Game.GameForeground;
import Game.GameMap;
import Game.GameObject;

public class Event {
    public enum Action {
        ADD_TILE,
        ADD_OBJECT,
        RM_OBJECT
    };

    public Action action;
    GameMap map;
    GameForeground object;
    public int x;
    public int y;
    String oldPath;
    boolean oldWalkable;
    String oldWeatherPath;

    public Event(Action action, GameMap map, GameForeground object) {
        this.action = action;
        this.map = map;
        this.object = object;
    }

    public Event(Action action, GameMap map, int x, int y) {
        this.action = action;
        this.map = map;
        this.x = x;
        this.y = y;
        oldPath = map.getPathTile(x, y);
        oldWalkable = map.isWalkable(x, y);
        oldWeatherPath = map.getPathWeatherTile(x, y);
    }
}
