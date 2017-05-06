import com.google.gson.Gson;

public class Main {
    public static void main(String[] args)
    {
        Gson json = new Gson();
        World test = new World("Xar'tsaroth");
        GameMap map1 = new GameMap("Map1", 10, 10, "");
        test.addMap(map1);
        GameMap map2 = new GameMap("Map2", 10, 10, "");
        test.addMap(map2);
        test.deleteMap(0);
        test.getMap(1).setPathTile(0,0,"nouveau path");
        String jsonstring = json.toJson(test);
        System.out.println(jsonstring);
        EditorModel editorModel = new EditorModel();
        EditorView editorView = new EditorView();
        EditorController editorController = new EditorController(editorModel, editorView);
        editorController.control();
    }
}
