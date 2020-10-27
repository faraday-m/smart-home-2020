package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;
import sun.management.Sensor;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class HallDoorEventProcessor implements EventProcessor {
    private void turnLightsOff(Room room) {
        for (Light light : room.getLights()) {
            light.setActive(false);
            SensorCommand command = new SimpleSensorCommand(CommandType.LIGHT_OFF, light.getId());
            command.sendCommand();
        }
    }

    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                if (room.getName().equals("hall")) {
                    Door door = room.getDoor(event.getObjectId());
                    if (door != null) {
                        smartHome.getRooms().forEach(this::turnLightsOff);
                    }
                }
            }
        }
    }
}
