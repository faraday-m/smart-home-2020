package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.HomeComponent;

public class GetHallDoorAction implements Action {
    private ComponentId doorId = null;

    public GetHallDoorAction() { }

    public ComponentId getDoorId() {
        return doorId;
    }

    @Override
    public void accept(HomeComponent component) {
        doorId = component.getId();
    }
}
