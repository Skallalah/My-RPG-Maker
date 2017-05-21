package GameWindow;

import Common.Executor;
import Common.Observer;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameView extends JFrame implements Observer{

    public JProgressBar jProgressBar;
    public JPanel jPanelProgressBar;
    public LayoutManager layoutManager;
    public boolean stop;

    public GameView(GameModel model) {
        gameModel_ = model;
        stop = false;
        setTitle(model.getWorld().getName());



        setSize(500, 400);
        Executor.executor.submit(new Runnable() {
            @Override
            public void run() {
                //   jPanelProgressBar = new JPanel();
                jProgressBar = new JProgressBar();
                Border border = BorderFactory.createTitledBorder("Loading...");
                jProgressBar.setBounds(60,60,300, 40);
                jProgressBar.setBorder(border);

                jProgressBar.setValue(0);
                jProgressBar.setStringPainted(true);
                layoutManager = getLayout();
                setLayout(new GridBagLayout());
                add(jProgressBar);
                //   add(jPanelProgressBar);
                setVisible(true);

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
                            jProgressBar.setVisible(false);
                            remove(jProgressBar);
                            setLayout(layoutManager);
                            gameMapViewer_ = new GameMapViewer(model);
                            JPanel map_panel = gameMapViewer_;
                            add(map_panel);


                            map_panel.repaint();
                            return;
                        }
                    }
                });
                timer.start();
            }
        });



            setVisible(true);
            model.addObserver(this);

    }


    private GameMapViewer gameMapViewer_;
    private GameModel gameModel_;

    @Override
    public void update(String str) {
    }
}
