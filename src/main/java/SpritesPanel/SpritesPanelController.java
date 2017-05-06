package SpritesPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
            public void mouseClicked (MouseEvent e) {
                model.detectSprite(e.getX(), e.getY(), view);
            }
        });
    }
}
