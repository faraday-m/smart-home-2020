package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class RoomEvent extends SensorEvent {
    public RoomEvent(EventType type, ComponentId objectId) {
        super(type, objectId);
    }
}
