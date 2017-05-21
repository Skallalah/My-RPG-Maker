package SpritesPanel;

import java.awt.*;
import java.awt.Event;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import Common.*;
import EditorWindow.EditorModel;
import EditorWindow.EditorView;
import EditorWindow.Walkable;
import MapPanel.MapPanelController;
import MapPanel.MapPanelModel;
import MapPanel.MapPanelView;

import javax.imageio.ImageIO;

public class SpritesPanelModel implements Observable {
    private ArrayList<Observer> observerList = new ArrayList<>();

    public ArrayList<BufferedImage> images = new ArrayList<>();
    private boolean foregroundSprites;
    private HashMap<String, File> pathImages = new HashMap<>();
    public String path;

    public SpritesPanelModel(String path, boolean foregroundSprites) {
        this.foregroundSprites = foregroundSprites;
        this.path = path;
        loadSprites(path);
    }
    public boolean isForegroundSprite() {
        return foregroundSprites;
    }
    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    public void load() {
        images = new ArrayList<>();
        loadSprites(path);
    }

    private void loadSprites(String path) {
        File folder = new File(path);
        for(File fileEntry : folder.listFiles())
            if (!fileEntry.isDirectory()) {
                try {
                    BufferedImage img = ImageIO.read(fileEntry);
                    if (img.getHeight() % 16 == 0 && img.getWidth() % 16 == 0) {
                        pathImages.put(fileEntry.getName(), fileEntry);
                        images.add(img);
                        String imagePath = fileEntry.getPath().replace("\\", "/");
                        SpriteResources.imageToPath.put(img, imagePath);
                        SpriteResources.pathToImage.put(imagePath, img);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                loadSprites(fileEntry.getPath());
            }
    }

    public void addImage(String path) {
        try {
            BufferedImage img = ImageIO.read(new File(path));
            if (img.getHeight() % 16 == 0 && img.getWidth() % 16 == 0) {
                images.add(img);
                String imagePath = path.replace("\\", "/");
                SpriteResources.imageToPath.put(img, imagePath);
                SpriteResources.pathToImage.put(imagePath, img);
                notifyObserver("repaint");
            }
            else
                notifyObserver("errorOnSprite");
        } catch (Exception e) {
            notifyObserver("errorOnSprite");
        }
    }

    public void fillSelection(String imagePath) {
        if (!SpriteResources.foregroundSprites) {
            Rectangle r = EditorProperties.selection;
            int x = r.x / 16;
            int y = r.y / 16;
            int w = (r.width / 16) + x;
            int h = (r.height / 16) + y;
            for (int j = y; j < h; j++) {
                for (int i = x; i < w; i++) {
                    History.addEvent(new Common.Event(Common.Event.Action.ADD_TILE, EditorProperties.currentMap, i, j));
                    EditorProperties.currentMap.setPathTile(i, j, imagePath);
                    EditorProperties.currentMap.setWalkable(i, j, EditorProperties.walkable != Walkable.NON_WALKABLE);
                    EditorProperties.currentMap.setPathWeatherTile(i, j, EditorProperties.weatherTile);
                }
            }
            EditorProperties.selection = null;
            SpriteResources.selectedSprite = null;
            notifyObserver("repaint");
        }
    }

    public void updateImages(String wordEntered) {
        images = new ArrayList<>();
        if (wordEntered.equals("")) {
            loadSprites(path);
        }
        else {
            notifyObserver("clear");
            wordEntered = wordEntered + ".png";
            for (String wordStocked : pathImages.keySet()) {
                if (wordEntered.equals(wordStocked)) {
                    try {
                        BufferedImage img = ImageIO.read(pathImages.get(wordStocked));
                        if (img.getHeight() % 16 == 0 && img.getWidth() % 16 == 0) {
                            pathImages.put(pathImages.get(wordStocked).getName(), pathImages.get(wordStocked));
                            images.add(img);
                            String imagePath = pathImages.get(wordStocked).getPath().replace("\\", "/");
                            SpriteResources.imageToPath.put(img, imagePath);
                            SpriteResources.pathToImage.put(imagePath, img);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
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
