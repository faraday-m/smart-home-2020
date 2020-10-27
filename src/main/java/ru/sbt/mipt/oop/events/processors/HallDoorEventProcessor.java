package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.GetHallDoorEvent;
import ru.sbt.mipt.oop.events.HallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, Event event) {
        Event getHallDoor = new GetHallDoorEvent();
        smartHome.apply(getHallDoor, c -> {}); //Получаем айди двери в холле
        if (event.getType() == DOOR_CLOSED && event.getObjectId().equals(getHallDoor.getObjectId())) {
            smartHome.apply(new HallDoorEvent(EventType.LIGHTS_OFF, event.getObjectId()), ((HomeComponent c) -> {
                ((Light)c).setOn(false);
                SensorCommand command = new SimpleSensorCommand(CommandType.LIGHT_OFF, c.getId());
                command.sendCommand();
            }));
        }
    }
}
