package GameWindow;

import Common.Executor;
import Common.Observer;

import javax.imageio.ImageIO;
import javax.sql.StatementEventListener;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameView extends JFrame implements Observer{

    JButton playButton;
    JButton cancelButton;
    public JPanel jpanelGame;
    JPanel jpanelBackGround;
    JPanel backupPane;
    BufferedImage img;
    public GameModel model_;


    public JProgressBar jProgressBar;
    public JPanel jPanelProgressBar;
    public LayoutManager layoutManager;
    public GameView self = this;
    public Timer timer;
    public boolean stop;

    public GameView(GameModel model) {
        this.model_ = model;



        setResizable(false);
        File folder = new File("resources/icons/gamepage.jpg");

        img = null;
        try {
            img = ImageIO.read(folder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(img.getWidth(),img.getHeight());

        jpanelBackGround = background(new JPanel(), img, getSize());
        jpanelGame = new JPanel();

        jpanelGame.setLayout(new GridBagLayout());
        backupPane = (JPanel)getContentPane();

        setContentPane(jpanelBackGround);

        playButton = new JButton("Play");
        jpanelGame.add(playButton);

        cancelButton = new JButton("Cancel");

        jpanelGame.add(cancelButton);

        add(jpanelGame);
        jpanelGame.setVisible(true);
        setVisible(true);
    }


    public void playGame() {

        remove(jpanelGame);
        setContentPane(backupPane);
        model_.addObserver(this);

        gameModel_ = model_;
        setTitle(model_.getWorld().getName());
        setSize(500, 400);
        gameMapViewer_ = new GameMapViewer(model_);
        JPanel map_panel = gameMapViewer_;

        jPanelProgressBar = new JPanel();
        jProgressBar = new JProgressBar();
        Border border = BorderFactory.createTitledBorder("Loading...");
        jProgressBar.setBounds(60,60,300, 40);
        jProgressBar.setBorder(border);

        jProgressBar.setValue(0);
        jProgressBar.setStringPainted(true);
        // layoutManager = getLayout();
        jPanelProgressBar.setLayout(new GridBagLayout());
        jPanelProgressBar.add(jProgressBar);
        add(jPanelProgressBar);
        setVisible(true);

        Executor.executor.submit(new Runnable() {
            @Override
            public void run() {

                Timer timer = new Timer(10, new ActionListener() {
                    int percentage = 0;
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        if (percentage != 101) {
                            jProgressBar.setValue(percentage);
                            percentage++;
                        } else {
                            Timer t = (Timer) actionEvent.getSource();
                            t.stop();
                            jPanelProgressBar.setVisible(false);
                            remove(jPanelProgressBar);
                            //            setLayout(layoutManager);
                            add(map_panel);
                            map_panel.repaint();


                            map_panel.setVisible(true);
                            model_.addObserver(self);
                            setFocusable(true);
                            requestFocusInWindow();

                        }
                    }
                });
                timer.start();
            }
        });
    }

    public void cancelGame() {
        dispose();
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

    public JButton getPlayButton() { return playButton; }
    public JButton getCancelButton() { return cancelButton; }

    private GameMapViewer gameMapViewer_;
    private GameModel gameModel_;

    @Override
    public void update(String str) {
    }
}
