package NewFrame;

import Common.Observable;
import Common.Observer;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;
import Game.GameMap;
import MapPanel.MapPanelController;
import MapPanel.MapPanelModel;
import MapPanel.MapPanelView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class NewModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    public NewModel(){

    }

    public void submitAction() {
        notifyObserver("submit");
    }

    public void cancelAction() {
        notifyObserver("cancel");
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
