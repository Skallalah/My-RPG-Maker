package MapPanel;

import Common.Executor;
import Common.SpriteResources;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MapPanelController {
    private MapPanelModel model;
    private MapPanelView view;

    public MapPanelController(MapPanelModel model, MapPanelView view) {
        this.model = model;
        this.view = view;
    }

    public void control() {
        view.addMouseListener (new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                Executor.executor.submit(() -> {
                    clickAction(e.getX()/16, e.getY()/16);
                });
            }

        });

        view.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Executor.executor.submit(() -> {
                    clickAction(e.getX()/16, e.getY()/16);
                });
            }
        });
    }

    private void clickAction(int x, int y) {
        if (SpriteResources.selectedSprite.equals("remove"))
            model.removeObjects(x,y);
        else
            model.addObject(x, y);
    }
}
