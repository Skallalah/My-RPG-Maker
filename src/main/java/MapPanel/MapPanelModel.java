package MapPanel;

import Game.GameMap;
import Game.GameObject;

public class MapPanelModel {
    GameMap map;

    public MapPanelModel(GameMap map) {
        this.map = map;
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }

    public String getDefaultTile() {
        return "resources/sprites/backgroundTile/grass.png";
    }

    public void addObject(int x, int y) {
        map.addObject(new GameObject(x, y, ""));
    }
}
