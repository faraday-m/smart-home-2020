package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.elements.DeviceId;

public class SensorEvent {
    private final SensorEventType type;
    private final DeviceId objectId;

    public SensorEvent(SensorEventType type, DeviceId objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEventType getType() {
        return type;
    }

    public DeviceId getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }
}
