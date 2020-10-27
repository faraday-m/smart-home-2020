package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

public interface EventProcessor {
    void processEvent(SmartHome smartHome, Event event);
}
