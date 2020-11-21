package ru.sbt.mipt.oop.events.processors;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEventAdapter;

public class EventHandlerAdapter implements EventHandler {
    private EventProcessor eventProcessor;

    public EventHandlerAdapter(AlarmNotifierDecorator alarmNotifierDecorator) {
        this.eventProcessor = alarmNotifierDecorator;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        Event eventAdapter = new SensorEventAdapter(event);
        eventProcessor.processEvent(eventAdapter);
    }
}
