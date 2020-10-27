package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class GetHallDoorEvent implements Event {
    private EventType eventType;
    private ComponentId doorId;

    public GetHallDoorEvent() {
        this.eventType = EventType.GET_HALLDOOR;
    }

    public void setObjectId(ComponentId doorId) {
        this.doorId = doorId;
    }

    @Override
    public EventType getType() {
        return eventType;
    }

    @Override
    public ComponentId getObjectId() {
        return doorId;
    }
}
