import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class EditorController {
    private EditorModel model;
    private EditorView view;

    public EditorController(EditorModel model, EditorView view){
        this.model = model;
        this.view = view;
    }

    public void control(){

        view.getNewButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                NewFrame newFrame = new NewFrame();
            }
        });
        
        view.getOpenButton().addActionListener(new ActionListener() {
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
        });

        view.getSaveButton().addActionListener(new ActionListener() {
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
        });

        view.getMap().addMouseListener (new MouseAdapter() {
            @Override
            public void mouseClicked (MouseEvent e) {
                JOptionPane.showMessageDialog(
                        e.getComponent (), "X: " + e.getX () + ", Y: " + e.getY ());
            }
        });
    }
}
