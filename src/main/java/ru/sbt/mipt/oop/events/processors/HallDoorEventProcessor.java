package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.EventType.LIGHTS_OFF;


public class HallDoorEventProcessor implements EventProcessor {
    @Override
    public Event processEvent(SmartHome smartHome, Event event) {
        if (event.getType() == LIGHTS_OFF) {
            smartHome.apply(event, (HomeComponent c) -> ((Light) c).setOn(false));
        }
        return event;
    }
}
