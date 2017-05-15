package SpritesPanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import Common.Observable;
import Common.Observer;
import Common.SpriteResources;
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

    public SpritesPanelModel(String path, boolean foregroundSprites) {
        this.foregroundSprites = foregroundSprites;
        loadSprites(path);
    }
    public boolean isForegroundSprite() {
        return foregroundSprites;
    }
    public ArrayList<BufferedImage> getImages() {
        return images;
    }

    private void loadSprites(String path) {
        File folder = new File(path);
        for(File fileEntry : folder.listFiles())
            if (!fileEntry.isDirectory()) {
                try {
                    BufferedImage img = ImageIO.read(fileEntry);
                    if (img.getHeight() % 16 == 0 && img.getWidth() % 16 == 0) {
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
            Rectangle r = SpriteResources.selection;
            int x = r.x / 16;
            int y = r.y / 16;
            int w = (r.width / 16) + x;
            int h = (r.height / 16) + y;
            for (int i = x; i < w; i++) {
                for (int j = y; j < h; j++) {
                    SpriteResources.mapToRender.setPathTile(i, j, imagePath);
                    SpriteResources.mapToRender.setWalkable(j, i, SpriteResources.walkable != Walkable.NON_WALKABLE);
                }
            }
            SpriteResources.selection = null;
            SpriteResources.selectedSprite = null;
            EditorModel.getSelf().notifyObserver("repaint");
        }
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
