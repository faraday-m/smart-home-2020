package ru.sbt.mipt.oop.events.processors;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEventAdapter;

public class EventHandlerAdapter implements EventHandler {
    private EventDecorator eventDecorator;

    public EventHandlerAdapter(EventDecorator eventDecorator) {
        this.eventDecorator = eventDecorator;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        Event eventAdapter = new SensorEventAdapter(event);
        eventDecorator.processEvent(eventAdapter);
    }
}
