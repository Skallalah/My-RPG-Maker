package NewFrame;

import Common.Observer;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;
import Game.GameMap;
import MapPanel.MapPanelController;
import MapPanel.MapPanelModel;
import MapPanel.MapPanelView;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class NewView extends JFrame implements Observer {
    NewModel model;

    JTextField titleField;
    JTextField widthField;
    JTextField heightField;
    JButton submitButton;
    JButton cancelButton;
    BufferedImage img;

    public NewView(NewModel model){
        this.model = model;
        model.addObserver(this);

        File folder = new File("resources/icons/newpage.png");

        img = null;
        try {
            img = ImageIO.read(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle("New map");
        setSize(img.getWidth(),img.getHeight());

        JPanel jpanel = background(new JPanel(), img, getSize());

        setLayout(new GridBagLayout());
        setContentPane(jpanel);

        GridLayout grid = new GridLayout(4,2);
        JPanel gridpanel = new JPanel(grid);

        gridpanel.setPreferredSize(new Dimension(300,100));
        gridpanel.setBackground(new Color(0,0,0,0));

        gridpanel.setLocation(getWidth(), getHeight());
        JLabel ltitle = new JLabel("Title :");

        gridpanel.add(ltitle);
        titleField = new JTextField();
        titleField.setColumns(10);
        gridpanel.add(titleField);

        JLabel lwidth = new JLabel("Width :");
        gridpanel.add(lwidth, BorderLayout.NORTH);
        widthField = new JTextField();
        widthField.setColumns(10);
        gridpanel.add(widthField);

        JLabel lheight = new JLabel("Height :");
        gridpanel.add(lheight);
        heightField = new JTextField();
        heightField.setColumns(10);
        gridpanel.add(heightField);

        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        gridpanel.add(submitButton);
        gridpanel.add(cancelButton);

        add(gridpanel);
        gridpanel.setVisible(true);
        setVisible(true);

    }

    public static JPanel background(JPanel jpanel, BufferedImage img, Dimension d){
        jpanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                g.drawImage(img, 0, 0 , null);
            }
        };
        return jpanel;
    }

    public void newSubmitAction() {
        String title = getJTitle().getText();
        String width = getJwidth().getText();
        String height = getJheight().getText();
        if(!title.equals("") && !width.equals("") && !height.equals("")){
            GameMap tmpMap = new GameMap(title, Integer.parseInt(width), Integer.parseInt(height), "tests/resources/sprites/backgroundTile/grass.png");
            EditorModel.getSelf().getCurrentWorld().addMap(tmpMap);
            MapPanelModel mapPanelModel = new MapPanelModel(tmpMap);
            MapPanelView mapPanelView = new MapPanelView(mapPanelModel);
            MapPanelController mapPanelController = new MapPanelController(mapPanelModel, mapPanelView);
            mapPanelController.control();
            JScrollPane map = new JScrollPane(mapPanelView);
            map.setPreferredSize(new Dimension(0,0));
            EditorView.getMapPanel().add(title, map);
        }
        setVisible(false);
        dispose();
    }

    public void newCancelAction() {
        setVisible(false);
        dispose();
    }

    public JButton getSubmitbutton() {
        return submitButton;
    }
    public JButton getCancelbutton() {
        return cancelButton;
    }
    public JTextField getJTitle(){
        return titleField;
    }
    public JTextField getJwidth(){
        return widthField;
    }
    public JTextField getJheight(){
        return heightField;
    }

    @Override
    public void update(String str) {
        if (str.equals("submit")) {
            newSubmitAction();
        }
        else if (str.equals("cancel")) {
            newCancelAction();
        }
    }
}
