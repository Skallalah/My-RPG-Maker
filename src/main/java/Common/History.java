package Common;

import java.util.Stack;

public class History {
    private static Stack<Event> history = new Stack<>();
    private static Stack<Event> redoStack = new Stack<>();

    public static void addEvent(Event event) {
        history.push(event);
    }
    public static void addEventToRedoStack(Event event) {
        redoStack.push(event);
    }

    public static void undo() {
        Event event = history.pop();
        if (event.action == Event.Action.ADD_OBJECT) {
            event.map.getObjects().remove(event.object);
            event.action = Event.Action.RM_OBJECT;
            addEventToRedoStack(event);
        } else if (event.action == Event.Action.RM_OBJECT) {
            event.map.getObjects().add(event.object);
            event.action = Event.Action.ADD_OBJECT;
            addEventToRedoStack(event);
        } else if (event.action == Event.Action.ADD_TILE) {
            addEventToRedoStack(new Common.Event(Common.Event.Action.ADD_TILE, EditorProperties.currentMap, event.x, event.y));
            event.map.setPathTile(event.x, event.y, event.oldPath);
            event.map.setPathWeatherTile(event.x, event.y, event.oldWeatherPath);
            event.map.setWalkable(event.x, event.y, event.oldWalkable);
        }
    }

    public static void redo() {
        Event event = redoStack.pop();
        if (event.action == Event.Action.ADD_OBJECT) {
            event.map.getObjects().remove(event.object);
            event.action = Event.Action.RM_OBJECT;
            addEvent(event);
        } else if (event.action == Event.Action.RM_OBJECT) {
            event.map.getObjects().add(event.object);
            event.action = Event.Action.ADD_OBJECT;
            addEvent(event);
        } else if (event.action == Event.Action.ADD_TILE) {
            addEvent(new Common.Event(Common.Event.Action.ADD_TILE, EditorProperties.currentMap, event.x, event.y));
            event.map.setPathTile(event.x, event.y, event.oldPath);
            event.map.setPathWeatherTile(event.x, event.y, event.oldWeatherPath);
            event.map.setWalkable(event.x, event.y, event.oldWalkable);
        }
    }

}
