package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class HallDoorEvent extends SensorEvent {
    public HallDoorEvent(EventType type, ComponentId objectId) {
        super(type, objectId);
    }
}
