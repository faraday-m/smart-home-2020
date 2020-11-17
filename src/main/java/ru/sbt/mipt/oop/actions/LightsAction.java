package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;

public class LightsAction implements Action {
    private boolean isOn;

    public LightsAction(boolean isOn) {
        this.isOn = isOn;
    }

    @Override
    public void accept(HomeComponent component) {
        ((Light) component).setOn(isOn);
        SensorCommand command = new SimpleSensorCommand(CommandType.LIGHT_OFF, component.getId());
        command.sendCommand();
    }
}
