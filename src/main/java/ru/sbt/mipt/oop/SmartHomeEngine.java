package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.processors.*;

public class SmartHomeEngine implements Engine {
    private final EventDecorator eventDecorator;
    private final SensorEventGenerator sensorEventGenerator;

    public SmartHomeEngine(EventDecorator eventDecorator, SensorEventGenerator sensorEventGenerator) {
        this.eventDecorator = eventDecorator;
        this.sensorEventGenerator = sensorEventGenerator;
    }


    public void start() {
        // начинаем цикл обработки событий
        for (Event event = sensorEventGenerator.getNextEvent();
             event != null;
             event = sensorEventGenerator.getNextEvent()) {
            Event currentEvent = event;
            System.out.println("Got event: " + currentEvent);
            eventDecorator.processEvent(event);
        }
    }
}
