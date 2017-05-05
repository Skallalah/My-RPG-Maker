import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class EditorController {
    private EditorModel model;
    private EditorView view;
    private ActionListener actionListener;

    public EditorController(EditorModel model, EditorView view){
        this.model = model;
        this.view = view;
    }

    public void contol(){
        actionListener = new ActionListener() {
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

        view.getButton().addActionListener(actionListener);
    }
}
