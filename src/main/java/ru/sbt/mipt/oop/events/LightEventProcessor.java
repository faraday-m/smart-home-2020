package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class LightEventProcessor implements EventProcessor {
    public SensorEvent processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == SensorEventType.LIGHTS_OFF) {
            setAllLights(smartHome, false, CommandType.LIGHT_OFF);
        } else if (event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF) {
            for (Room room : smartHome.getRooms()) {
                Light light = room.getLight(event.getObjectId());
                if (light != null) {
                    room.changeLight(event.getObjectId(), (event.getType() == LIGHT_ON));
                }
            }
        }
        return event;
    }

    public void setAllLights(SmartHome smartHome, boolean isOn, CommandType lightCommand) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setActive(isOn);
                if (lightCommand != null) {
                    SensorCommand command = new SimpleSensorCommand(lightCommand, light.getId());
                    command.sendCommand();
                }
            }
        }
    }
}
