import javax.swing.*;
import java.awt.*;

public class NewFrame extends JFrame {
    public NewFrame() {
        setTitle("New map");
        setSize(300,250);
        setLayout(new GridBagLayout());

        add(new JLabel("Title :"));
        JTextField textField = new JTextField();
        textField.setColumns(10);
        add(textField);

        add(new JLabel("Width :"));
        textField = new JTextField();
        textField.setColumns(10);
        add(textField);

        add(new JLabel("Height :"));
        textField = new JTextField();
        textField.setColumns(10);
        add(textField);

        setVisible(true);
    }
}
