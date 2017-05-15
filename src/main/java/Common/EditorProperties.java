package Common;

import java.awt.Point;
import java.awt.Rectangle;

import EditorWindow.Walkable;
import Game.GameMap;

public class EditorProperties {
    public static Rectangle selection;
    public static int x;
    public static int y;
    public static GameMap mapToRender;
    public static Walkable walkable = Walkable.NONE;
    public static Point playerPosition;
}
