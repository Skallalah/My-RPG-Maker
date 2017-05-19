package SpritesPanel;

import Common.Executor;
import Common.Observer;
import Common.SpriteResources;
import EditorWindow.EditorModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import static java.lang.Integer.max;

public class SpritesPanelController {
    private SpritesPanelModel model;
    private SpritesPanelView view;
    private Cursor cursor;

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
                    String imagePath = detectSelectedSprite(e.getX(), e.getY());
                    if (imagePath != null) {
                        if (SpriteResources.selectedSprite == null)
                            SpriteResources.selectedSprite = imagePath;
                        else if (SpriteResources.selectedSprite.equals("select"))
                            model.fillSelection(imagePath);
                        else
                            SpriteResources.selectedSprite = imagePath;
                    }
                    else
                        SpriteResources.selectedSprite = null;
                    EditorModel.getSelf().notifyObserver("setCursor");
                });
            }
        });
    }

    public String detectSelectedSprite(int clickX, int clickY) {
        int x = 0;
        int y = 0;
        int maxy = 0;
        for (BufferedImage img : model.getImages()) {
            if (x + img.getWidth() > view.getWidth()) {
                x = 0;
                y = maxy + 16;
            }

            if (clickX >= x && clickX <= x + img.getWidth() && clickY >= y && clickY <= y + img.getHeight())
                return SpriteResources.imageToPath.get(img);

            x += img.getWidth() + 16;
            maxy = max(maxy, y + img.getHeight());
        }
        return null;
    }
}
