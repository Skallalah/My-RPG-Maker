package SpritesPanel;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Common.Executor;
import Common.Observable;
import Common.Observer;
import Common.SpriteResources;
import EditorWindow.EditorView;

public class SpritesPanelModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    private boolean foregroundSprites;

    public SpritesPanelModel(boolean foregroundSprites) {
        this.foregroundSprites = foregroundSprites;
    }

    public boolean isForegroundSprite() {
        return foregroundSprites;
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
