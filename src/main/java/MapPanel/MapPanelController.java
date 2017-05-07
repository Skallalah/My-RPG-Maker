package MapPanel;

import javax.swing.*;
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
        view.addMouseListener (new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                model.addObject(e.getX()/16, e.getY()/16);
            }
        });
    }
}