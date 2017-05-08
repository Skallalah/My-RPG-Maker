import EditorWindow.EditorController;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;
import Game.GameMap;
import Game.GameWorld;
import com.google.gson.Gson;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args)
    {
        Gson json = new Gson();
        GameWorld test = new GameWorld("Xar'tsaroth");
        GameMap map1 = new GameMap("Map1", 10, 10, "");
        test.addMap(map1);
        GameMap map2 = new GameMap("Map2", 10, 10, "");
        test.addMap(map2);
        test.deleteMap(0);
        test.getMap(1).setPathTile(0,0,"nouveau path");
        String jsonstring = json.toJson(test);
        System.out.println(jsonstring);

        ExecutorService executor = Executors.newFixedThreadPool(8);
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
