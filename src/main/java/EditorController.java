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
                model.openAction(view);
            }
        });

        view.getSaveButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.saveAction(view);
            }
        });

      /*  view.getWalkableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.walkAction(view);
            }
        });
        */

        view.getWalkableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.setwalkAction(view);
            }
        });

        view.getNotwalkableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.setnotwalkAction(view);
            }
        });

     /*   view.getNotwalkableButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.walkAction(view);
            }
        });
        */


    }
}
