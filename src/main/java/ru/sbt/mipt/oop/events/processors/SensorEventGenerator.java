package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.Application;
import ru.sbt.mipt.oop.elements.HomeElementType;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.typedefs.AlarmEventType;
import ru.sbt.mipt.oop.events.typedefs.DoorEventType;
import ru.sbt.mipt.oop.events.typedefs.EventType;
import ru.sbt.mipt.oop.events.typedefs.LightEventType;

import java.util.Random;

import static ru.sbt.mipt.oop.events.processors.EventProcessorType.DOOR;

public class SensorEventGenerator implements EventGenerator {
    public Random random = new Random();
    SmartHome smartHome;

    public SensorEventGenerator(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    public Event getNextEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.02) {
            System.out.println("End of generator cycle");
            return null; // null means end of event stream
        }
        EventType sensorEventType;
        String objectId;
        if (Math.random() < 0.1) {
            sensorEventType = AlarmEventType.ALARM_ACTIVATE;
            objectId = "ALARM";
            return new AlarmEvent(sensorEventType, new StringId(objectId), Application.ACTIVATION_CODE_1);
        } else if (Math.random() < 0.1) {
            sensorEventType = AlarmEventType.ALARM_DEACTIVATE;
            objectId = "ALARM";
            return new AlarmEvent(sensorEventType, new StringId(objectId), Application.ACTIVATION_CODE_1);
        } else if (Math.random() < 0.1) {
            sensorEventType = AlarmEventType.ALARM_ACTIVATE;
            objectId = "ALARM";
            return new AlarmEvent(sensorEventType, new StringId(objectId), Application.ACTIVATION_CODE_2);
        } else if (Math.random() < 0.1) {
            sensorEventType = AlarmEventType.ALARM_DEACTIVATE;
            objectId = "ALARM";
            return new AlarmEvent(sensorEventType, new StringId(objectId), Application.ACTIVATION_CODE_2);
        }
        if (Math.random() >= 0.5) {
            sensorEventType = DoorEventType.values()[random.nextInt(DoorEventType.values().length)];
            objectId = String.valueOf(random.nextInt(smartHome.getElementCount(HomeElementType.DOOR) + 1));
        } else {
            sensorEventType = LightEventType.values()[random.nextInt(LightEventType.values().length)];
            objectId = String.valueOf(random.nextInt(smartHome.getElementCount(HomeElementType.LIGHT) + 1));
        }

        if (sensorEventType.getProcessorType() == DOOR) {
            return new DoorEvent(sensorEventType, new StringId(objectId));
        } else {
            return new LightEvent(sensorEventType, new StringId(objectId));
        }
    }
}
