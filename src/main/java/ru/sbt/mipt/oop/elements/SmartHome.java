package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.actions.GetAlarmAction;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements HomeComponent {
    private Collection<Room> rooms;
    private AlarmSystem alarmSystem;

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
        return new StringId("HOME");
    }

    public void setAlarmSystem(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    public void apply(Action action) {
        alarmSystem.apply(action);
        rooms.forEach((Room r) -> r.apply(action));
    }
}
