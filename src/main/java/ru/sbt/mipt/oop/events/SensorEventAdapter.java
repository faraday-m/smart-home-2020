package ru.sbt.mipt.oop.events;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.processors.AlarmProcessor;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class SensorEventAdapter extends AlarmEvent {
    private CCSensorEvent sensorEvent;
    private static final String ALARM_CODE = "activationCode";

    public SensorEventAdapter(CCSensorEvent event) {
        super(getType(event), new StringId(event.getObjectId()), ALARM_CODE);
        this.sensorEvent = event;
    }

    @Override
    public EventType getType() {
        return getType(sensorEvent);
    }

    private static EventType getType(CCSensorEvent event) {
        switch (event.getEventType()) {
            case "LightIsOn":
                return EventType.LIGHT_ON;
            case "LightIsOff":
                return EventType.LIGHT_OFF;
            case "DoorIsOpen":
                return EventType.DOOR_OPEN;
            case "DoorIsClosed":
                return EventType.DOOR_CLOSED;
            case "DoorIsLocked":
                return EventType.ALARM_ACTIVATE;
            case "DoorIsUnlocked":
                return EventType.ALARM_DEACTIVATE;
            default:
                return null;
        }
    }

    @Override
    public ComponentId getObjectId() {
        if (!AlarmProcessor.isAlarmEvent(getType())) {
            return new StringId(sensorEvent.getObjectId());
        } else {
            return new StringId("ALARM");
        }
    }
}
