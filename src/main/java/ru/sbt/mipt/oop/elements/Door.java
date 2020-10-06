package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.DoorEventType;

public class Door implements HomeComponent {
    private ComponentId id;

    public ComponentId getId() {
        return id;
    }

    private boolean isOpen;

    public Door(ComponentId id, boolean isOpen) {
        this.id = id;
        this.isOpen = isOpen;
    }

    public Door(String id, boolean isOpen) {
        this.id = new StringId(id);
        this.isOpen = isOpen;
    }


    public void setActive(boolean open) {
        isOpen = open;
        System.out.println("Door " + id + " was " + (open ? "opened" : "closed"));
    }

    @Override
    public ElementType getType() {
        return HomeElementType.DOOR;
    }

    @Override
    public Event apply(Event event, Action action) {
        if (event.getType() instanceof DoorEventType || event.getObjectId().equals(this.id)) {
            action.accept(this);
        }
        return event;
    }
}
