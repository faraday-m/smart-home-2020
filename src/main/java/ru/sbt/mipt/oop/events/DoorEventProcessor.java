package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.Room;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor{
    public SensorEvent processEvent(SmartHome smartHome, SensorEvent event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            for (Room room : smartHome.getRooms()) {
                Door door = room.getDoor(event.getObjectId());
                if (door != null) {
                    room.changeDoor(door.getId(), (event.getType() == DOOR_OPEN));
                    if (room.getName().equals("hall") && event.getType() == DOOR_CLOSED) {
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        return new SensorEvent(SensorEventType.LIGHTS_OFF, event.getObjectId());
                    }
                }
            }
        }
        return event;
    }
}
