package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SensorCommand;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.GetHallDoorEvent;
import ru.sbt.mipt.oop.events.HomeEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, Event event) {
        if (event.getType().isDoorEvent()) {
            Event inputEvent = event;
            smartHome.apply(inputEvent, ((HomeComponent c) -> ((Door) c).setOpen(inputEvent.getType() == DOOR_OPEN)));
            if (event.getType() == DOOR_CLOSED) {
                Event getHallDoor = new GetHallDoorEvent();
                smartHome.apply(getHallDoor, c -> {
                }); //Получаем айди двери в холле
                if (event.getObjectId().equals(getHallDoor.getObjectId())) {
                    smartHome.apply(new HomeEvent(EventType.HOME_LIGHTS_OFF, SmartHome.id), ((HomeComponent c) -> {
                        ((Light) c).setOn(false);
                        SensorCommand command = new SimpleSensorCommand(CommandType.LIGHT_OFF, c.getId());
                        command.sendCommand();
                    }));
                }
            }
        }
    }
}
