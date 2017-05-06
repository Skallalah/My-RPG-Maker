import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditorModel {
    public EditorModel() {

    }

    public void openFile(File file) {

    }

    public void newAction() {
        NewFrame newFrame = new NewFrame();
    }

    public void openAction(EditorView view) {
        JFileChooser fileChooser = new JFileChooser();
        int returnVal = fileChooser.showOpenDialog(view);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            File[] files = fileChooser.getSelectedFiles();
            for(int i = 0; i < files.length; i++) {
                openFile(files[i]);
            }
        }
    }

    public void saveAction(EditorView view) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose a file that you want to save");
        int returnVal = fileChooser.showSaveDialog(view);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File saveFile = fileChooser.getSelectedFile();
            //TODO : STOCK WHAT WE HAVE TO SAVE FOR THE WANTED FILE
            String saving = "";
            try {
                FileWriter f2 = new FileWriter(saveFile, false);
                f2.write(saving);
                f2.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
