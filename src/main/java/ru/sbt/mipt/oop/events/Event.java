package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public interface Event {
    EventType getType();
    ComponentId getObjectId();
}
