package SpritesPanel;

import java.awt.image.BufferedImage;
import static java.lang.Integer.max;

public class SpritesPanelModel {
    int selectedSprite;

    public SpritesPanelModel() {
        selectedSprite = -1;
    }

    public void detectSprite(int clickX, int clickY, SpritesPanelView view) {
        selectedSprite = detectSelectedSprite(clickX, clickY, view);
    }

    public int detectSelectedSprite(int clickX, int clickY, SpritesPanelView view) {
        int x = 0;
        int y = 0;
        int maxy = 0;
        for (BufferedImage img : view.images) {
            if (x + img.getWidth() > view.dimension.getWidth()) {
                x = 0;
                y = maxy + 16;
            }

            if (clickX >= x && clickX <= x + img.getWidth() && clickY >= y && clickY <= y + img.getHeight())
                return view.images.indexOf(img);

            x += img.getWidth() + 16;
            maxy = max(maxy, img.getHeight());
        }
        return -1;
    }
}
