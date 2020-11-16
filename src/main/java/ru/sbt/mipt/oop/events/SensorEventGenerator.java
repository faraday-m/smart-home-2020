package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.elements.DeviceId;
import ru.sbt.mipt.oop.elements.StringId;

import java.util.Random;

public class SensorEventGenerator {
    public Random random = new Random();

    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType;
        do {
            sensorEventType = SensorEventType.values()[random.nextInt(SensorEventType.values().length)];
        } while (sensorEventType == SensorEventType.LIGHTS_OFF);
        String objectId = String.valueOf(random.nextInt(10));
        return new SensorEvent(sensorEventType, new StringId(objectId));
    }
}
