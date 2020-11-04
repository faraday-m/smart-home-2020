package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, Event event) {
        if (event.getType().isDoorEvent()) {
            Event inputEvent = event;
            smartHome.apply(inputEvent, ((HomeComponent c) -> ((Door) c).setOpen(inputEvent.getType() == DOOR_OPEN)));
        }
    }
}
