package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.events.EventProcessor;

public class Door extends SmartDevice{
    private boolean isOpen;

    public Door(DeviceId id, boolean isOpen) {
        super(id);
        this.isOpen = isOpen;
    }

    public Door(String id, boolean isOpen) {
        super(id);
        this.isOpen = isOpen;
    }


    public void setActive(boolean open) {
        isOpen = open;
    }
}
