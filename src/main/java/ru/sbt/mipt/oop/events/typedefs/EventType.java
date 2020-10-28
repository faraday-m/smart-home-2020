package ru.sbt.mipt.oop.events.typedefs;

public enum EventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED, LIGHTS_OFF, GET_HALLDOOR,
    ALARM_ACTIVATE, ALARM_DEACTIVATE, ALARM_WARNING, GET_ALARM_STATE;

    public boolean isLightEvent() {
        return (this.equals(LIGHT_OFF) || this.equals(LIGHT_ON));
    }

    public boolean isDoorEvent() {
        return (this.equals(DOOR_OPEN) || this.equals(DOOR_CLOSED));
    }

    public boolean isAlarmEvent() {
        return (this.equals(ALARM_ACTIVATE) || this.equals(ALARM_DEACTIVATE) || this.equals(ALARM_WARNING) || this.equals(GET_ALARM_STATE));
    }

}
