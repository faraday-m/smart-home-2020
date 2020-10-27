package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;

import java.util.ArrayList;
import java.util.List;

public class SmartHomeEngine implements Engine {
    private final List<EventProcessor> processors;
    private final SensorEventGenerator sensorEventGenerator;
    SmartHome smartHome;

    private void fillProcessors() {
        processors.add(new DoorEventProcessor());
        processors.add(new LightEventProcessor());
        processors.add(new HallDoorEventProcessor());
    }

    public SmartHomeEngine(SmartHome smartHome, SensorEventGenerator sensorEventGenerator) {
        this.smartHome = smartHome;
        this.processors = new ArrayList<>();
        this.sensorEventGenerator = sensorEventGenerator;
        fillProcessors();
    }

    public void start() {
        // начинаем цикл обработки событий
        for (SensorEvent event = sensorEventGenerator.getNextSensorEvent();
             event != null;
             event = sensorEventGenerator.getNextSensorEvent()) {
            SensorEvent currentEvent = event;
            System.out.println("Got event: " + currentEvent);
            processors.forEach(p -> p.processEvent(smartHome, currentEvent));
        }
    }
}
