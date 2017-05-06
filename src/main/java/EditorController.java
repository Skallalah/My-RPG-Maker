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
                model.newAction();
            }
        });

        view.getOpenButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                model.openAction(view, model);
            }
        });

        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.saveAction(view);
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
