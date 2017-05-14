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
            String oldPath = event.map.getPathTile(event.x, event.y);
            event.map.setPathTile(event.x, event.y, event.oldPath);
            event.oldPath = oldPath;
            addEventToRedoStack(event);
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
            String oldPath = event.map.getPathTile(event.x, event.y);
            event.map.setPathTile(event.x, event.y, event.oldPath);
            event.oldPath = oldPath;
            addEvent(event);
        }
    }

}
