package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.SmartHome;

import java.awt.*;
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

    public SensorEvent processEvent(SmartHome smartHome, SensorEvent event) {
        if (event == null) {
            event = sensorEventGenerator.getNextSensorEvent();
        }
        Queue<SensorEvent> events = new LinkedBlockingQueue<>();
        events.add(event);
        while (events.size() > 0) {
            SensorEvent proceedingEvent = events.remove();
            System.out.println("Got event: " + proceedingEvent);
            List<SensorEvent> newEvents = processors.stream().map(p -> p.processEvent(smartHome, proceedingEvent)).filter(e -> !e.equals(proceedingEvent)).collect(Collectors.toList());
            events.addAll(newEvents);
        }
        return sensorEventGenerator.getNextSensorEvent();
    }

}
