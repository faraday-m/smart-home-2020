package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.EventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.typedefs.EventType.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {
    public void processEvent(SmartHome smartHome, Event event) {
        if (event.getType().isLightEvent()) {
            smartHome.apply(event, (HomeComponent c) -> ((Light) c).setOn(event.getType() == LIGHT_ON));
        }
    }
}
