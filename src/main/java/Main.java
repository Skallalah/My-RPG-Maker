import Common.Executor;
import EditorWindow.EditorController;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;
import Game.GameMap;
import Game.GameWorld;
import com.google.gson.Gson;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args)
    {
        ExecutorService executor = Executors.newFixedThreadPool(8);

        Executor.executor = Executors.newFixedThreadPool(8);
        EditorModel editorModel = new EditorModel();
        EditorView editorView = new EditorView(editorModel);
        EditorController editorController = new EditorController(editorModel, editorView);
        editorController.control();
    }
}
