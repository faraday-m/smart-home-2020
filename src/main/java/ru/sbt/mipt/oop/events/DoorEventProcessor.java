package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.Room;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                Door door = room.getDoor(event.getObjectId());
                if (door != null) {
                    room.changeDoor(door.getId(), (event.getType() == DOOR_OPEN));
                }
            }
        }
    }
}
