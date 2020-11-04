package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.EventType.*;

public class Light implements HomeComponent {
    private ComponentId id;
    private boolean isOn;

    public Light(String id, boolean isOn) {
        this.id = new StringId(id);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
        System.out.println("Light " + id + " was turned " + (on ? "on" : "off"));
    }

    @Override
    public ElementType getType() {
        return HomeElementType.LIGHT;
    }

    @Override
    public ComponentId getId() {
        return id;
    }

    @Override
    public void apply(Event event, Action action) {
        if (event.getObjectId().equals(id)) {
            action.accept(this);
        } else if (event.getType().isHomeEvent() || event.getType().isRoomEvent()) {
            action.accept(this);
        }
    }
}
