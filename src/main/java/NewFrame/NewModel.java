package NewFrame;

//import EditorWindow.EditorView;

import EditorWindow.EditorView;
import Game.GameMap;
import MapPanel.MapPanelController;
import MapPanel.MapPanelModel;
import MapPanel.MapPanelView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by najjaj_k on 5/8/17.
 */
public class NewModel {


    JScrollPane map;
    JTabbedPane panelmap;

public NewModel(){

}


   public void newSubmitAction(NewView newView, EditorView view) {
    System.out.println("EnterSubmit");
        //panelmap = view.getpanelmap();

        String title = newView.getJTitle().getText();
        String width = newView.getJwidth().getText();
        String height = newView.getJheight().getText();
        if(!title.equals("") && !width.equals("") && !height.equals("")){
            GameMap tmpMap = new GameMap(title, Integer.parseInt(width), Integer.parseInt(height), "resources/sprites/backgroundTile/grass.png");
            //view.getCurrent_world().addMap(tmpMap);
            MapPanelModel mapPanelModel = new MapPanelModel(tmpMap);
            MapPanelView mapPanelView = new MapPanelView(mapPanelModel);
            MapPanelController mapPanelController = new MapPanelController(mapPanelModel, mapPanelView);
            mapPanelController.control();
            map = new JScrollPane(mapPanelView);
            map.setPreferredSize(new Dimension(0,0));
            panelmap.add(title, map);
        }
       newView.setVisible(false);
       newView.dispose();
    }

    public void newCancelAction(NewView newView, EditorView view) {
        newView.setVisible(false);
        newView.dispose();
    }

}
