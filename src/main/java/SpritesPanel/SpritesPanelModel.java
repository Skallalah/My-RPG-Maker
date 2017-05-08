package SpritesPanel;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import EditorWindow.EditorView;
import SpriteResources.SpriteResources;

public class SpritesPanelModel {
    EditorView editorWindow;

    public SpritesPanelModel(EditorView editorWindow) {
        this.editorWindow = editorWindow;
    }

    public void setAsSelected(String selectedSprite) {
        SpriteResources.selectedSprite = selectedSprite;
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = SpriteResources.pathToImage.get(selectedSprite);
        Cursor c = toolkit.createCustomCursor(image , new Point(0, 0), "img");
        editorWindow.setCursor(c);
    }
}
