package SpritesPanel;

import Common.Executor;
import Common.Observer;
import Common.SpriteResources;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static java.lang.Integer.max;

public class SpritesPanelController {
    private SpritesPanelModel model;
    private SpritesPanelView view;

    public SpritesPanelController(SpritesPanelModel model, SpritesPanelView view) {
        this.model = model;
        this.view = view;
    }

    public void control() {
        view.addMouseListener (new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Executor.executor.submit(() -> {
                    SpriteResources.foregroundSprites = model.isForegroundSprite();
                    SpriteResources.selectedSprite = detectSelectedSprite(e.getX(), e.getY());
                    // SET CURSOR
                });
            }
        });
    }

    public String detectSelectedSprite(int clickX, int clickY) {
        int x = 0;
        int y = 0;
        int maxy = 0;
        for (BufferedImage img : SpriteResources.images) {
            if (SpriteResources.imageToPath.get(img).startsWith(view.pathFilter)) {
                if (x + img.getWidth() > 300) {
                    x = 0;
                    y = maxy + 16;
                }

                if (clickX >= x && clickX <= x + img.getWidth() && clickY >= y && clickY <= y + img.getHeight())
                    return SpriteResources.imageToPath.get(img);

                x += img.getWidth() + 16;
                maxy = max(maxy, img.getHeight());
            }
        }
        return null;
    }
}
