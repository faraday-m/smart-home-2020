package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;

public class LightAction implements Action {
    private boolean isOn;
    private ComponentId id;

    public LightAction(ComponentId id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public ComponentId getId() {
        return id;
    }

    @Override
    public void accept(HomeComponent component) {
        ((Light) component).setOn(isOn);
    }
}
