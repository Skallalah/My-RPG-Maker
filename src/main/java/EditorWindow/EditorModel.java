package EditorWindow;

import Common.EditorProperties;
import Common.Observable;
import Common.Observer;
import Common.SpriteResources;
import Game.GameWorld;
import MapPanel.MapPanelModel;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;

public class EditorModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    private static EditorModel self;
    private GameWorld current_world_;
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
            setCurrentWorld(gson.fromJson(reader, GameWorld.class));
            notifyObserver("removeAllTabs");
            notifyObserver("addAllMaps");
        } catch (Exception e) {
            notifyObserver("errorOnFile");
        }
    }

    public void saveFile(String path, EditorView view) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(getCurrentWorld());
        try(  PrintWriter out = new PrintWriter( path )  ){
            out.println(jsonString);
        } catch (Exception e) {
            notifyObserver("errorOnFile");
        }
    }

    public void setWalkable(Walkable newWalkable) {
        if (EditorProperties.walkable == Walkable.NONE) {
            if (newWalkable == Walkable.WALKABLE)
                notifyObserver("walkable");
            else if (newWalkable == Walkable.NON_WALKABLE)
                notifyObserver("nonwalkable");
            EditorProperties.walkable = newWalkable;
            MapPanelModel.fillSelectionWithProperties(true);
        }
        else {
            notifyObserver("walkable_none");
            EditorProperties.walkable = Walkable.NONE;
        }

        notifyObserver("repaint");
    }

    public void newProject() {
        notifyObserver("removeAllTabs");
        setCurrentWorld(new GameWorld("New world"));
    }

    public void exit() {
        notifyObserver("exit");
    }

    public void setEraserCursor() {
        notifyObserver("setEraserCursor");
    }

    public void setCharacCursor() {
        notifyObserver("setCharacCursor");
    }

    public void search() {
        notifyObserver("search");
    }

    public void repaint() {
        notifyObserver("repaint");
    }

    public void errorOnPlayerPosition() {
        notifyObserver("errorOnPlayerPosition");
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
