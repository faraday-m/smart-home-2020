package ru.sbt.mipt.oop.elements;

public class Light extends SmartDevice {
    private boolean isOn;

    public Light(String id, boolean isOn) {
        super(id);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setActive(boolean on) {
        isOn = on;
    }
}
