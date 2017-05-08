package MapPanel;

import javafx.scene.input.MouseDragEvent;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.ExecutorService;

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
                model.addObject(e.getX()/16, e.getY()/16);
                model.setDefaultCursor();
                view.revalidate();
                view.repaint();
            }

        });

        view.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                model.addObject(e.getX()/16, e.getY()/16);
                model.setDefaultCursor();
                view.revalidate();
                view.repaint();
            }
        });


    }
}
