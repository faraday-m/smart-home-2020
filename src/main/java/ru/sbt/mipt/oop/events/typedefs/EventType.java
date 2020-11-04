package ru.sbt.mipt.oop.events.typedefs;

public enum EventType {
    LIGHT_ON, LIGHT_OFF, DOOR_OPEN, DOOR_CLOSED, HOME_LIGHTS_OFF, HOME_LIGHTS_ON, ROOM_LIGHTS_OFF, ROOM_LIGHTS_ON,
    GET_HALLDOOR, ALARM_ACTIVATE, ALARM_DEACTIVATE, ALARM_WARNING, GET_ALARM_STATE;

    public boolean isLightEvent() {
        return (this.equals(LIGHT_OFF) || this.equals(LIGHT_ON));
    }

    public boolean isDoorEvent() {
        return (this.equals(DOOR_OPEN) || this.equals(DOOR_CLOSED));
    }

    public boolean isAlarmEvent() {
        return (this.equals(ALARM_ACTIVATE) || this.equals(ALARM_DEACTIVATE) || this.equals(ALARM_WARNING) || this.equals(GET_ALARM_STATE));
    }
    public boolean isHomeEvent() {
        return (this.equals(HOME_LIGHTS_ON) || this.equals(HOME_LIGHTS_OFF));
    }
    public boolean isRoomEvent() {
        return (this.equals(ROOM_LIGHTS_ON) || this.equals(ROOM_LIGHTS_OFF));
    }

}
