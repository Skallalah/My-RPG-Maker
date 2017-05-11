package NewFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by najjaj_k on 5/8/17.
 */
public class NewView extends JFrame {
    JFrame newFrame;
    JTextField titleField;
    JTextField widthField;
    JTextField heightField;
    JButton submitButton;
    JButton cancelButton;
    BufferedImage img;

    public NewView(){
        File folder = new File("resources/icons/newpage.png");

        img = null;
        try {
            img = ImageIO.read(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setTitle("New map");
        setSize(img.getWidth(),img.getHeight());
       // setLayout(new BorderLayout());

        JPanel jpanel = background(new JPanel(), img, getSize());



        setLayout(new GridBagLayout());
        setContentPane(jpanel);
        //setContentPane(new JLabel(new ImageIcon(img)));


        //setLayout(new FlowLayout());

        GridLayout grid = new GridLayout(4,2);
        JPanel gridpanel = new JPanel(grid);

        gridpanel.setPreferredSize(new Dimension(300,100));
        gridpanel.setBackground(new Color(0,0,0,0));

        gridpanel.setLocation(getWidth(), getHeight());
        JLabel ltitle = new JLabel("Title :");
       /* Font font = new Font(ltitle.getFont().getName(), Font.BOLD, ltitle.getFont().getSize());
        ltitle.setFont(font);
        ltitle.setSize(20,20);
        */
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

}
