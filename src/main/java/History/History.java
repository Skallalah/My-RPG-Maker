package History;

import java.util.Queue;

public class History {
    private enum Action {
        ADD_OBJECT,
        RM_OBJECT,
        SET_AS_WALKABLE,
        SET_AS_NONWALKABLE
    };

    class Event {
        public Action action;
        public int value;
    }

    private Queue<Event> history;

    public void addEvent(Event event) {
        history.add(event);
    }

    public void undo() {
        Event event = history.poll();
        // FIXME undo event
    }
}
