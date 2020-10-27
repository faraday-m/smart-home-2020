package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.HomeElementType;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.Random;

import static ru.sbt.mipt.oop.events.typedefs.EventType.*;

public class SensorEventGenerator implements EventGenerator {
    public Random random = new Random();
    SmartHome smartHome;

    public SensorEventGenerator(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public Event getNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.01) {
            System.out.println("End of generator cycle");
            return null; // null means end of event stream
        }
        EventType sensorEventType;
        String objectId;
        double rand = Math.random();
        if (rand >= 0.5) {
            if (rand < 0.25) {
                sensorEventType = DOOR_OPEN;
            } else {
                sensorEventType = DOOR_CLOSED;
            }
            objectId = String.valueOf(random.nextInt(5));
        } else {
            if (rand > 0.75) {
                sensorEventType = LIGHT_OFF;
            } else {
                sensorEventType = LIGHT_ON;
            }
            objectId = String.valueOf(random.nextInt(10));
        }

        if (sensorEventType == DOOR_OPEN || sensorEventType == DOOR_CLOSED) {
            return new DoorEvent(sensorEventType, new StringId(objectId));
        } else {
            return new LightEvent(sensorEventType, new StringId(objectId));
        }
    }
}
