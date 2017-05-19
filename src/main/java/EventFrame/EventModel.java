package EventFrame;

import Common.Observable;
import Common.Observer;

import java.util.ArrayList;

/**
 * Created by najjaj_k on 5/16/17.
 */
public class EventModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    public EventModel() {

    }

    public void doneAction() {
        notifyObserver("done");
    }

    public void cancelAction() {
        notifyObserver("cancel");
    }

    public void teleport() {
        notifyObserver("teleport");
    }

    public void dialog() {
        notifyObserver("dialog");
    }

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
