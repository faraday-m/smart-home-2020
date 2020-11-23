package ru.sbt.mipt.oop.events.processors;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.SensorEventAdapter;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.Map;

public class EventHandlerTransformer implements EventHandler {
    private EventProcessor eventProcessor;
    private Map<String, EventType> eventTypeMap;

    public EventHandlerTransformer(EventProcessor eventProcessor, Map<String, EventType> eventTypeMap) {
        this.eventProcessor = eventProcessor;
        this.eventTypeMap = eventTypeMap;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        Event eventAdapter = new SensorEventAdapter(event, eventTypeMap.get(event.getEventType()));
        eventProcessor.processEvent(eventAdapter);
    }
}
