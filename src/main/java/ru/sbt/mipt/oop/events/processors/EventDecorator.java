package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.AlarmAction;
import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.*;

import java.util.List;

public class EventDecorator implements EventHandler {
    private List<EventProcessor> processors;

    public EventDecorator(List<EventProcessor> eventProcessors) {
        this.processors = eventProcessors;
    }

    public void registerEventProcessor(EventProcessor processor) {
        processors.add(processor);
    }

    public void handleEvent(CCSensorEvent event) {
        Event eventAdapter = new SensorEventAdapter(event);
        processEvent(eventAdapter);
    }

    public void processEvent(Event event) {
        processors.forEach(p -> p.processEvent(event));
    }
}
