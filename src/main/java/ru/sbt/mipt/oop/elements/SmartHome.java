package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.Event;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComponent {
    private Collection<Room> rooms;
    private AlarmSystem alarmSystem;
    public static final ComponentId id = new StringId("HOME");

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }
    public SmartHome(Collection<Room> rooms, AlarmSystem alarmSystem) {
        this.rooms = rooms;
        this.alarmSystem = alarmSystem;
    }
    @Override
    public ElementType getType() {
        return HomeElementType.SMART_HOME;
    }

    @Override
    public ComponentId getId() {
        return id;
    }

    public void setAlarmSystem(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void apply(Event event, Action action) {
        if (event.getType().isAlarmEvent()) {
            alarmSystem.apply(event, action);
        }
        rooms.forEach((Room r) -> r.apply(event, action));
    }

}
