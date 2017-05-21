package GameWindow;

import Common.Observable;
import Common.Observer;
import Game.GameCharacter;
import Game.GameMap;
import Game.GameObject;
import Game.GameWorld;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by skallalah on 09/05/17.
 */
public class GameModel implements Observable {
    public boolean speedForce;

    public GameModel(GameWorld world) {
        world_ = world;
        this.speedForce = false;
    }




    public GameWorld getWorld() {
        return world_;
    }

    public GameMap getCurrent_map() {
        return world_.getCharacter().get_map(world_);
    }

    public void move_character(KeyEvent keyPressed) {
        if (world_.in_dialogue()) {
            return;
        }
        world_.getCharacter().next_state();
        if (keyPressed.getKeyCode() == 83) {
            speedForce = !speedForce;
        }
        if (keyPressed.getKeyCode() == 38) {
            world_.getCharacter().setCur_direction_(GameCharacter.direction.UP);
            if (speedForce)
                world_.getCharacter().moveUp(world_);
            world_.getCharacter().moveUp(world_);
        }
        else if (keyPressed.getKeyCode() == 39) {
            world_.getCharacter().setCur_direction_(GameCharacter.direction.RIGHT);
            if (speedForce)
                world_.getCharacter().moveRight(world_);
            world_.getCharacter().moveRight(world_);
        }
        else if (keyPressed.getKeyCode() == 37) {
            world_.getCharacter().setCur_direction_(GameCharacter.direction.LEFT);
            if (speedForce)
                world_.getCharacter().moveLeft(world_);
            world_.getCharacter().moveLeft(world_);
        }
        else if (keyPressed.getKeyCode() == 40) {
            world_.getCharacter().setCur_direction_(GameCharacter.direction.DOWN);
            if (speedForce)
                world_.getCharacter().moveDown(world_);
            world_.getCharacter().moveDown(world_);
        }  else {
            System.out.println("STOP");
            return;
        }
        int x =  world_.getCharacter().getX();
        int y =  world_.getCharacter().getY();
        GameObject cur = getCurrent_map().has_script(x, y);
        if ((cur != null) && (cur.get_touch() != null)){
            cur.get_touch().execute(world_);
        }
        notifyObserver("repaint");
    }

    public void idle_state() {
        world_.getCharacter().idle_state();
        notifyObserver("repaint");
    }

    public void interaction() {
        if (world_.in_dialogue()) {
            world_.end_dialogue();
        } else {
            GameObject cur = null;
            int x =  world_.getCharacter().getX();
            int y =  world_.getCharacter().getY();
            if (world_.getCharacter().getCur_direction() == GameCharacter.direction.UP) {
                cur = getCurrent_map().has_script(x, y - 1);
            } else if (world_.getCharacter().getCur_direction() == GameCharacter.direction.DOWN) {
                cur = getCurrent_map().has_script(x, y + 1);
            } else if (world_.getCharacter().getCur_direction() == GameCharacter.direction.RIGHT) {
                cur = getCurrent_map().has_script(x + 1, y);
            } else {
                cur = getCurrent_map().has_script(x - 1, y);
            }
            if ((cur == null) || (cur.get_interaction() == null)) {
                return;
            }
            cur.get_interaction().execute(world_);
        }
        notifyObserver("dialogue");
    }

    public void inventory() {
        notifyObserver("inventory");
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
