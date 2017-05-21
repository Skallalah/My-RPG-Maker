package EventFrame;

import Common.Observable;
import Common.Observer;
import Common.SpriteResources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by najjaj_k on 5/16/17.
 */
public class EventView extends JFrame implements Observer{
    EventModel eventModel;
    JComboBox sprites;
    JComboBox events;
    JComboBox actions;
    JButton submitButton;
    JButton cancelButton;
    JTextField mapId;
    JTextField positionX;
    JTextField positionY;
    JTextField dialog;
    JTextField name;
    JPanel panel;

    public EventView(EventModel eventModel) {
        this.eventModel = eventModel;
        eventModel.addObserver(this);

        setSize(300, 300);
        setLayout(new GridBagLayout());

        panel = new JPanel(new GridLayout(8, 1));

        sprites = new JComboBox();
        getFiles("resources", sprites);
        panel.add(sprites);

        String[] eventChoices = {"[TELEPORTER] Teleport", "[NPC] Dialog"};
        events = new JComboBox(eventChoices);
        panel.add(events);

        String[] actionChoices = {"On touch", "On action", "Both"};
        actions = new JComboBox(actionChoices);
        panel.add(actions);

        mapId = new JTextField();
        mapId.setColumns(30);
        mapId.setText("Map id");
        panel.add(mapId);

        positionX = new JTextField();
        positionX.setColumns(30);
        positionX.setText("X position");
        panel.add(positionX);

        positionY = new JTextField();
        positionY.setColumns(30);
        positionY.setText("Y position");
        panel.add(positionY);

        dialog = new JTextField();
        dialog.setColumns(30);
        dialog.setText("Dialog");

        name = new JTextField();
        name.setColumns(30);
        name.setText("NPC's name");

        submitButton = new JButton("Done");
        panel.add(submitButton);

        cancelButton = new JButton("Cancel");
        panel.add(cancelButton);

        add(panel);
        setVisible(true);
    }

    private void getFiles(String path, JComboBox comboBox) {
        File folder = new File(path);
        for(File fileEntry : folder.listFiles())
            if (!fileEntry.isDirectory()) {
                comboBox.addItem(fileEntry.getPath());
            }
            else {
                getFiles(fileEntry.getPath(), comboBox);
            }
    }

    public JComboBox getSprites() {
        return sprites;
    }

    public JComboBox getEvents() {
        return events;
    }

    public JComboBox getActions() {
        return actions;
    }

    public JTextField getMapId() {
        return mapId;
    }

    public JTextField getPosX() {
        return positionX;
    }

    public JTextField getPosY() {
        return positionY;
    }

    public JTextField getDialog() {
        return dialog;
    }

    public JButton getSubmitButton() {
        return submitButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void teleportSettings() {
        panel.removeAll();
        panel.add(sprites);
        panel.add(events);
        panel.add(actions);

        panel.add(mapId);

        panel.add(positionX);

        panel.add(positionY);

        panel.add(submitButton);
        panel.add(cancelButton);

        setVisible(true);
    }

    public void dialogSettings() {
        panel.removeAll();
        panel.add(sprites);
        panel.add(events);
        panel.add(actions);

        panel.add(dialog);
        panel.add(name);

        panel.add(submitButton);
        panel.add(cancelButton);

        setVisible(true);
    }

    public void newSubmitAction() {
        dispose();
    }

    public void newCancelAction() {
        dispose();
    }

    @Override
    public void update(String str) {
        if (str.equals("done")) {
            newSubmitAction();
        }
        else if (str.equals("cancel")) {
            newCancelAction();
        }
        if (str.equals("teleport")) {
            teleportSettings();
        }
        else if (str.equals("dialog")) {
            dialogSettings();
        }
    }
}
