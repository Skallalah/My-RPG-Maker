package SpritesPanel;

import java.awt.image.BufferedImage;

import SpriteResources.SpriteResources;

public class SpritesPanelModel {
    public SpritesPanelModel() {

    }

    public void setAsSelected(String selectedSprite) {
        SpriteResources.selectedSprite = selectedSprite;
        System.out.println(selectedSprite);
    }
}
