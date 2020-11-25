package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;

public class RoomLightsAction implements Action {
    private ComponentId roomName;
    private boolean isOn;

    public RoomLightsAction(ComponentId roomName, boolean isOn) {
        this.roomName = roomName;
        this.isOn = isOn;
    }

    public ComponentId getRoomName() {
        return roomName;
    }

    @Override
    public void accept(HomeComponent component) {
        if (component instanceof Light) {
            ((Light) component).setOn(isOn);
        }
    }
}
