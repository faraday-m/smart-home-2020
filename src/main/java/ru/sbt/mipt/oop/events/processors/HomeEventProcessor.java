package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.events.Event;

public abstract class HomeEventProcessor implements EventProcessor {
    protected ActionHandler actionHandler;

    public abstract void processEvent(Event event);
}
