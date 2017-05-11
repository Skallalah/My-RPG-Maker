package MapPanel;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import Common.Observable;
import Common.Observer;
import Common.SpriteResources;
import Game.GameForeground;
import Game.GameMap;

public class MapPanelModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    public GameMap map;

    public MapPanelModel(GameMap map) {
        this.map = map;
    }

    public int getWidth() {
        return map.getWidth();
    }

    public int getHeight() {
        return map.getHeight();
    }

    public void addObject(int x, int y) {
        BufferedImage img = SpriteResources.pathToImage.get(SpriteResources.selectedSprite);
        if (img != null) {
            if (SpriteResources.foregroundSprites)
                map.addObject(new GameForeground((x - img.getWidth() / 32), (y - img.getHeight() / 32), SpriteResources.selectedSprite));
            else
                map.setPathTile(x, y, SpriteResources.selectedSprite);
        }
        notifyObserver("repaint");
    }

    public void removeObjects(int x, int y) {
        Vector<GameForeground> objects = map.getObjects();
        for (GameForeground obj : objects) {

            String path = obj.getPath();
            BufferedImage img = SpriteResources.pathToImage.get(path);

            int ox = obj.getX();
            int oy = obj.getY();
            int dx = img.getWidth() / 16;
            int dy = img.getHeight() / 16;

            if (x >= ox && x <= ox + dx && y >= oy && y <= oy + dy)
                objects.remove(obj);
        }
        notifyObserver("repaint");
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
