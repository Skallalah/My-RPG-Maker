package NewFrame;

import EditorWindow.EditorView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NewController {
    private NewView view;
    private NewModel model;

    public NewController(NewView view, NewModel model){
        this.view = view;
        this.model = model;
    }

    public void control() {
        view.getSubmitbutton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.submitAction();
            }
        });

        view.getCancelbutton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                model.cancelAction();
            }
        });
    }
}
