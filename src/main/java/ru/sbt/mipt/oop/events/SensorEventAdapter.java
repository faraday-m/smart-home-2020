package ru.sbt.mipt.oop.events;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class SensorEventAdapter implements Event{
    private CCSensorEvent sensorEvent;

    public SensorEventAdapter(CCSensorEvent event) {
        this.sensorEvent = event;
    }

    @Override
    public EventType getType() {
        switch (sensorEvent.getEventType()) {
            case "LightIsOn":
                return EventType.LIGHT_ON;
            case "LightIsOff":
                return EventType.LIGHT_OFF;
            case "DoorIsOpen":
                return EventType.DOOR_OPEN;
            case "DoorIsClosed":
                return EventType.DOOR_CLOSED;
            case "DoorIsLocked":
                return EventType.DOOR_LOCKED;
            case "DoorIsUnlocked":
                return EventType.DOOR_UNLOCKED;
            default:
                return null;
        }
    }

    @Override
    public ComponentId getObjectId() {
        return new StringId(sensorEvent.getObjectId());
    }
}
