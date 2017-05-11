package EditorWindow;

import Common.Executor;
import Common.Observable;
import Common.Observer;
import Common.SpriteResources;
import Game.GameMap;
import Game.GameWorld;
import GameWindow.GameController;
import GameWindow.GameModel;
import GameWindow.GameView;
import MapPanel.MapPanelController;
import MapPanel.MapPanelModel;
import MapPanel.MapPanelView;
import NewFrame.NewController;
import NewFrame.NewModel;
import NewFrame.NewView;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import javax.swing.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Hashtable;

public class EditorModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    private static EditorModel self;
    private GameWorld current_world_;
    private boolean walkable;
    private static boolean gridDisplay;

    public EditorModel() {
        gridDisplay = true;
        self = this;
    }

    public void setCurrentWorld(GameWorld current_world) {
        current_world_ = current_world;
    }
    public GameWorld getCurrentWorld() {
        return current_world_;
    }
    public boolean getGridDisplay() {
        return gridDisplay;
    }
    public void toggleGridDisplay() {
        gridDisplay = !gridDisplay;
        notifyObserver("repaint");
    }
    public static EditorModel getSelf() {return self;}


    public void openFile(String path) {
        Gson gson = new Gson();
        JsonReader reader = null;
        try {
            reader = new JsonReader(new FileReader(path));
            GameWorld openWorld = gson.fromJson(reader, GameWorld.class);
            notifyObserver("removeAllTabs");
            Hashtable<Integer, GameMap> maps = openWorld.getMaps();
            for (GameMap map : maps.values()) {
                addMap(map);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveFile(String path, EditorView view) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(getCurrentWorld());
        try(  PrintWriter out = new PrintWriter( path )  ){
            out.println(jsonString);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static private void addMap(GameMap map) {
        MapPanelModel mapPanelModel = new MapPanelModel(map);
        MapPanelView mapPanelView = new MapPanelView(mapPanelModel);
        MapPanelController mapPanelController = new MapPanelController(mapPanelModel, mapPanelView);
        mapPanelController.control();
        JScrollPane map2 = new JScrollPane(mapPanelView);
        map2.setPreferredSize(new Dimension(0,0));
        // FIXME
        //view.rightPanel.addTab(map.getName(), map2);
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
        if (walkable)
            notifyObserver("walkable");
        else
            notifyObserver("nonwalkable");
    }

    @Override
    public void addObserver(Observer obs) {
        observerList.add(obs);
    }

    @Override
    public void notifyObserver(String str) {
        for(Observer obs : observerList)
            obs.update(str);
    }

    @Override
    public void removeObserver() {
        observerList = new ArrayList<>();
    }
}
