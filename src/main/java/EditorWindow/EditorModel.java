package EditorWindow;

import Common.Observable;
import Common.Observer;
import Game.GameWorld;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;

public class EditorModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    private static EditorModel self;
    private GameWorld current_world_;
    private Walkable walkable;
    private static boolean gridDisplay;

    public EditorModel() {
        gridDisplay = true;
        walkable = Walkable.NONE;
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

    public void setWalkable(Walkable newWalkable) {
        if (walkable == Walkable.NONE) {
            if (newWalkable == Walkable.WALKABLE)
                notifyObserver("walkable");
            else if (newWalkable == Walkable.NON_WALKABLE)
                notifyObserver("nonwalkable");
            walkable = newWalkable;
        }
        else {
            notifyObserver("walkable_none");
            walkable = Walkable.NONE;
        }

        notifyObserver("repaint");
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
