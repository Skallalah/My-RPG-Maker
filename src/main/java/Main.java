public class Main {
    public static void main(String[] args)
    {
        EditorModel editorModel = new EditorModel();
        EditorView editorView = new EditorView();
        EditorController editorController = new EditorController(editorModel, editorView);
        editorController.contol();
    }
}
