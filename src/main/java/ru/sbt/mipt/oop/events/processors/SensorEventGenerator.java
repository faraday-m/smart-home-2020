package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.Random;

import static ru.sbt.mipt.oop.events.typedefs.EventType.*;

public class SensorEventGenerator implements EventGenerator {
    SmartHome smartHome;

    public SensorEventGenerator(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public Event getNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        double random = Math.random();
        if (random < 0.02) {
            System.out.println("End of generator cycle");
            return null; // null means end of event stream
        }
        EventType sensorEventType;
        String objectId;

        if (random < 0.025) {
            sensorEventType = EventType.ALARM_ACTIVATE;
            objectId = "ALARM";
            return new AlarmEvent(sensorEventType, new StringId(objectId), Application.ACTIVATION_CODE_1);
        } else if (random < 0.05) {
            sensorEventType = EventType.ALARM_DEACTIVATE;
            objectId = "ALARM";
            return new AlarmEvent(sensorEventType, new StringId(objectId), Application.ACTIVATION_CODE_1);
        } else if (random < 0.075) {
            sensorEventType = EventType.ALARM_ACTIVATE;
            objectId = "ALARM";
            return new AlarmEvent(sensorEventType, new StringId(objectId), Application.ACTIVATION_CODE_2);
        } else if (random < 0.1) {
            sensorEventType = EventType.ALARM_DEACTIVATE;
            objectId = "ALARM";
            return new AlarmEvent(sensorEventType, new StringId(objectId), Application.ACTIVATION_CODE_2);
        }
        if (random >= 0.55) {
            if (random < 0.275) {
                sensorEventType = DOOR_OPEN;
            } else {
                sensorEventType = DOOR_CLOSED;
            }
            objectId = String.valueOf(new Random().nextInt(5));
        } else {
            if (random > 0.825) {
                sensorEventType = LIGHT_OFF;
            } else {
                sensorEventType = LIGHT_ON;
            }
            objectId = String.valueOf(new Random().nextInt(10));
        }

        if (sensorEventType == DOOR_OPEN || sensorEventType == DOOR_CLOSED) {
            return new DoorEvent(sensorEventType, new StringId(objectId));
        } else {
            return new LightEvent(sensorEventType, new StringId(objectId));
        }
    }
}
