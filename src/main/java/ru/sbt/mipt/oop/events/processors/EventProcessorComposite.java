package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

import java.util.*;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

public class EventProcessorComposite {
    private final SensorEventGenerator sensorEventGenerator;
    private final List<EventProcessor> processors;

    public EventProcessorComposite(SensorEventGenerator eventGenerator) {
        sensorEventGenerator = eventGenerator;
        this.processors = new ArrayList<>();
    }

    public void addEventProcessor(EventProcessor processor) {
        processors.add(processor);
    }

    public Event processEvent(SmartHome smartHome, Event event) {
        if (event == null) {
            event = sensorEventGenerator.getNextEvent();
        }
        Queue<Event> events = new LinkedBlockingQueue<>();
        events.add(event);
        while (events.size() > 0) {
            ru.sbt.mipt.oop.events.Event proceedingEvent = events.remove();
            System.out.println("Got event: " + proceedingEvent);
            List<Event> newEvents = processors.stream().map(p -> p.processEvent(smartHome, proceedingEvent)).filter(e -> !e.equals(proceedingEvent)).collect(Collectors.toList());
            events.addAll(newEvents);
        }
        return sensorEventGenerator.getNextEvent();
    }

}
