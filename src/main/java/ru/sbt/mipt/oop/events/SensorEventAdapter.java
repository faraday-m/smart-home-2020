package ru.sbt.mipt.oop.events;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.processors.AlarmProcessor;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.Map;

import static ru.sbt.mipt.oop.events.typedefs.EventType.*;
import static ru.sbt.mipt.oop.events.typedefs.EventType.GET_ALARM_STATE;

public class SensorEventAdapter implements Event, ActivationCodeOwner {
    private static final String ALARM_CODE = "activationCode";
    private EventType type;
    private ComponentId objectId;
    private String activationCode;

    public SensorEventAdapter(CCSensorEvent event, EventType type) {
        this.objectId = new StringId(event.getObjectId());
        this.activationCode = ALARM_CODE;
        this.type = type;
    }

    @Override
    public EventType getType() {
        return type;
    }

    private boolean isAlarmEvent(EventType type) {
        return (type.equals(ALARM_ACTIVATE) || type.equals(ALARM_DEACTIVATE) || type.equals(ALARM_WARNING) || type.equals(GET_ALARM_STATE));
    }

    @Override
    public ComponentId getObjectId() {
        if (!isAlarmEvent(getType())) {
            return objectId;
        } else {
            return new StringId("ALARM");
        }
    }

    @Override
    public String getActivationCode() {
        return activationCode;
    }
}
