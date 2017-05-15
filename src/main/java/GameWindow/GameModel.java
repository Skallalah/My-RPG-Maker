package GameWindow;

import Common.Observable;
import Common.Observer;
import Game.GameMap;
import Game.GameWorld;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameModel implements Observable {
    public GameModel(GameWorld world) {
        world_ = world;
    }

    public GameWorld getWorld() {
        return world_;
    }

    public GameMap getCurrent_map() {
        return world_.getCharacter().get_map(world_);
    }

    public void move_character(KeyEvent keyPressed) {
            System.out.println(keyPressed.getKeyCode());
            if (keyPressed.getKeyCode() == 38) {
                world_.getCharacter().moveUp(world_);
            }
            else if (keyPressed.getKeyCode() == 39) {
                world_.getCharacter().moveRight(world_);
            }
            else if (keyPressed.getKeyCode() == 37) {
                world_.getCharacter().moveLeft(world_);
            }
            else {
                world_.getCharacter().moveDown(world_);
            }
            System.out.println(world_.getCharacter().getX());
    }

    private GameWorld world_;
    private ArrayList<Observer> observerList = new ArrayList<>();

    @Override
    public void addObserver(Observer obs) {
        observerList.add(obs);
    }

    @Override
    public void notifyObserver(String str) {
        for(Observer obs : observerList)
            obs.update(str);
    }

    @Override
    public void removeObserver() {
        observerList = new ArrayList<>();
    }
}
