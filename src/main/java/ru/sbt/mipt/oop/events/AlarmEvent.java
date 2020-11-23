package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class AlarmEvent implements Event, ActivationCodeOwner {
    private final EventType type;
    private final ComponentId objectId;
    private final String activationCode;

    public AlarmEvent(EventType type, ComponentId id, String activationCode) {
        this.type = type;
        this.objectId = id;
        this.activationCode = activationCode;
    }
    @Override
    public EventType getType() {
        return type;
    }

    @Override
    public ComponentId getObjectId() {
        return objectId;
    }

    public String getActivationCode() { return activationCode; }

    @Override
    public String toString() {
        return "AlarmEvent{" +
                "type=" + type +
                ", objectId='" + objectId +
                ", activationCode='" + activationCode + '\'' +
                '}';
    }
}
