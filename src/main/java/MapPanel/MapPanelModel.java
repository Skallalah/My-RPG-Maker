package MapPanel;

import java.awt.Cursor;

import EditorWindow.EditorView;
import Game.GameForeground;
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

    public void addObject(int x, int y) {
        map.addObject(new GameForeground(x, y, SpriteResources.selectedSprite));
    }

    public void setDefaultCursor() {
        editorWindow.setCursor(Cursor.getDefaultCursor());
    }
}
