package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class GetAlarmStateEvent implements Event {
    private AlarmState state;
    private ComponentId id;

    public GetAlarmStateEvent() {
        this.id = new StringId("ALARM");
        this.state = null;
    }

    public void setState(AlarmState state) {
        this.state = state;
    }

    public AlarmState getState() {
        return state;
    }

    @Override
    public EventType getType() {
        return EventType.GET_ALARM_STATE;
    }

    @Override
    public ComponentId getObjectId() {
        return id;
    }
}
