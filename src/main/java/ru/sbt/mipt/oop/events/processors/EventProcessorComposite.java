package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.EventGenerator;
import ru.sbt.mipt.oop.events.typedefs.ProcessorType;

import java.util.*;
import java.util.stream.Collectors;

public class EventProcessorComposite implements EventProcessor {
    private final EventGenerator eventGenerator;
    private final Map<ProcessorType, List<EventProcessor>> processors;

    public EventProcessorComposite(SmartHome smartHome) {
        eventGenerator = new SensorEventGenerator(smartHome);
        this.processors = new LinkedHashMap<>();
    }

    public void addEventProcessor(ProcessorType type, EventProcessor processor) {
        processors.putIfAbsent(type, new ArrayList<>());
        if (!processors.get(type).contains(processor)) {
            processors.get(type).add(processor);
        }
    }

    public Event processEvent(SmartHome smartHome, Event event) {
        if (event == null) { //first iteration
            event = eventGenerator.getNextEvent();
        }
        if (event != null) {
            System.out.println("Got event: " + event);
            Event finalEvent = event;
            List<Event> resultEvents = processors.get(event.getType().getProcessorType()).stream().map(p -> p.processEvent(smartHome, finalEvent)).collect(Collectors.toList());
            if (resultEvents.size() == 1 && resultEvents.get(0).equals(event)) {
                return eventGenerator.getNextEvent();
            }
            return resultEvents.get(resultEvents.size()-1); //пока что такой логики достаточно
        } else {
            return null;
        }
    }

}
