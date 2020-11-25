package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;

public class Light implements HomeComponent {
    private ComponentId id;
    private boolean isOn;

    public Light(String id, boolean isOn) {
        this.id = new StringId(id);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
        System.out.println("Light " + id + " was turned " + (on ? "on" : "off"));
    }

    @Override
    public ComponentId getId() {
        return id;
    }

    @Override
    public void apply(Action action) {
        action.accept(this);
    }
}
