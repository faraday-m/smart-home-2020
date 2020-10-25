package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.HallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_OPEN;
import static ru.sbt.mipt.oop.events.typedefs.EventType.LIGHTS_OFF;

public class DoorEventProcessor implements EventProcessor {
    public Event processEvent(SmartHome smartHome, Event event) {
        if (event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED) {
            Event inputEvent = event;
            event = smartHome.apply(inputEvent, ((HomeComponent c) -> ((Door) c).setOpen(inputEvent.getType() == DOOR_OPEN)));
            if (event != inputEvent && event.getType() == LIGHTS_OFF) {
                event = new HallDoorEvent(LIGHTS_OFF, smartHome.getId(), new SimpleSensorCommand(CommandType.LIGHT_OFF, smartHome.getId()));
            }
        }
        return event;
    }
}
