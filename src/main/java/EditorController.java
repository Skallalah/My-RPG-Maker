import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditorController {
    private EditorModel model;
    private EditorView view;
    private ActionListener actionOpenListener;
    private ActionListener actionNewListener;
    private ActionListener actionSaveListener;

    public EditorController(EditorModel model, EditorView view){
        this.model = model;
        this.view = view;
    }

    public void control(){
        actionOpenListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser fileChooser = new JFileChooser();
                int returnVal = fileChooser.showOpenDialog(view);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    File[] files = fileChooser.getSelectedFiles();
                    for(int i = 0; i < files.length; i++) {
                        model.openFile(files[i]);
                    }
                }
            }
        };

        actionNewListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
              //  JFileChooser fileChooser = new JFileChooser();
           //     fileChooser.setDialogTitle("Create a new file");
              //  int returnVal = fileChooser;

            }
        };

        actionSaveListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
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
        };

        view.getOpenButton().addActionListener(actionOpenListener);
        view.getNewButton().addActionListener(actionNewListener);
        view.getSaveButton().addActionListener(actionSaveListener);
    }
}
