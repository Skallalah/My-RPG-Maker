package NewFrame;

import EditorWindow.EditorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by najjaj_k on 5/8/17.
 */
public class NewController {
    private NewView newView;
    private NewModel newModel;
    private EditorView view;

    public NewController(NewView newView, NewModel newModel, EditorView view){
        this.newView = newView;
        this.newModel = newModel;
        this.view = view;
    }

    public void control() {
        System.out.println("Control");

        newView.getSubmitbutton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newModel.newSubmitAction(newView, view);
            }
        });

        newView.getCancelbutton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                newModel.newCancelAction(newView, view);
            }
        });

    }
}
