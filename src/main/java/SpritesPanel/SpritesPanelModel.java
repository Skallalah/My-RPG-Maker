package SpritesPanel;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

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
        BufferedImage img = SpriteResources.pathToImage.get(selectedSprite);
        Cursor c = toolkit.createCustomCursor(img, new Point(img.getWidth() / 2, img.getHeight() / 2), "img");
        editorWindow.setCursor(c);
    }
}
