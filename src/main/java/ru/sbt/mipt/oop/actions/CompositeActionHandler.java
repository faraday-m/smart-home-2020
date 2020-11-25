package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;

public class CompositeActionHandler implements ActionHandler {
    private SmartHome smartHome;
    private AlarmSystem alarmSystem;

    public CompositeActionHandler(SmartHome smartHome, AlarmSystem alarmSystem) {
        this.smartHome = smartHome;
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void apply(Action action) {
        smartHome.apply(action);
    }
}
