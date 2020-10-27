package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class LightEvent extends SensorEvent {
    public LightEvent(EventType type, ComponentId objectId) {
        super(type, objectId);
    }
}
