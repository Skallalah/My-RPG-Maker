import EditorWindow.EditorController;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;
import Game.CharacterPersonalization.GameClass;
import Game.GameCharacter;
import Game.GameMap;
import Game.GameWorld;
import GameWindow.GameModel;
import GameWindow.GameView;
import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args)
    {
        Gson json = new Gson();
        GameWorld test = new GameWorld("Xar'tsaroth");
        GameMap map1 = new GameMap("Map1", 100, 100, "resources/sprites/backgroundTile/grass.png");
        test.addMap(map1);
        GameMap map2 = new GameMap("Map2", 50, 50, "resources/sprites/backgroundTile/grass.png");
        test.addMap(map2);
        test.deleteMap(0);
        test.getMap(1).setPathTile(0,0,"resources/sprites/backgroundTile/grass.png");
        String jsonstring = json.toJson(test);
        System.out.println(jsonstring);
        GameCharacter machin = new GameCharacter("Jean", "", new GameClass("Barbarian"));
        test.setCharacter_(machin);
        machin.spawn_player(1, 0, 0);
        ExecutorService executor = Executors.newFixedThreadPool(8);
        GameModel model = new GameModel(test);
        GameView view_ = new GameView(model);
        view_.repaint();
        executor.submit(new runEditor(executor));
    }

    static class runEditor implements Runnable {
        ExecutorService executor;

        runEditor(ExecutorService executor) {
            this.executor = executor;
        }

        @Override
        public void run() {
            EditorModel editorModel = new EditorModel(executor);
            EditorView editorView = new EditorView();
            EditorController editorController = new EditorController(editorModel, editorView);
            editorController.control();
        }
    }

}
