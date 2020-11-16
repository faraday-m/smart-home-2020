package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.events.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;

public abstract class SmartDevice {
    protected DeviceId id;

    public SmartDevice(DeviceId id) {
        this.id = id;
    }

    public SmartDevice(String id) {
        this.id = new StringId(id);
    }

    public DeviceId getId() {
        return id;
    }

    public String id() { return id.toString(); }
}
