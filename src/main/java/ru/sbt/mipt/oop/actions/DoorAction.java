package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;

public class DoorAction implements Action {
    private boolean isOpen;
    private ComponentId id;

    public DoorAction(ComponentId id, boolean isOpen) {
        this.id = id;
        this.isOpen = isOpen;
    }

    public ComponentId getId() {
        return id;
    }

    @Override
    public void accept(HomeComponent component) {
        if (component instanceof Door) {
            ((Door) component).setOpen(isOpen);
        }
    }
}
