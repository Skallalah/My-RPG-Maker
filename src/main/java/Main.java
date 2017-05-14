import Common.Executor;
import EditorWindow.EditorController;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;

import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args)
    {
        Executor.executor = Executors.newFixedThreadPool(8);

        EditorModel editorModel = new EditorModel();
        EditorView editorView = new EditorView(editorModel);
        EditorController editorController = new EditorController(editorModel, editorView);
        editorController.control();
    }
}
