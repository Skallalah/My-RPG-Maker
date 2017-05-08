package MapPanel;

import java.awt.Cursor;

import EditorWindow.EditorView;
import Game.GameMap;
import Game.GameObject;
import SpriteResources.SpriteResources;

public class MapPanelModel {
    public GameMap map;
    EditorView editorWindow;

    public MapPanelModel(GameMap map, EditorView editorWindow) {
        this.map = map;
        this.editorWindow = editorWindow;
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
        map.addObject(new GameObject(x, y, SpriteResources.selectedSprite));
    }

    public void setDefaultCursor() {
        editorWindow.setCursor(Cursor.getDefaultCursor());
    }
}
