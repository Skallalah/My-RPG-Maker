package MapPanel;

import java.awt.Cursor;
import java.awt.image.BufferedImage;

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
        BufferedImage img = SpriteResources.pathToImage.get(SpriteResources.selectedSprite);
        //map.addObject(new GameObject(x, y, SpriteResources.selectedSprite));
        map.addObject(new GameForeground((x - img.getWidth() / 32), (y - img.getHeight() / 32) , SpriteResources.selectedSprite));
    }

    public void setDefaultCursor() {
        editorWindow.setCursor(Cursor.getDefaultCursor());
    }
}
