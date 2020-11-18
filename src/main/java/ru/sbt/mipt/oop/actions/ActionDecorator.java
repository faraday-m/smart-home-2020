package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;

public class ActionDecorator implements ActionHandler {
    private SmartHome smartHome;
    private AlarmSystem alarmSystem;

    public ActionDecorator(SmartHome smartHome, AlarmSystem alarmSystem) {
        this.smartHome = smartHome;
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void apply(Action action) {
        if (alarmSystem.isDeactivated() || action instanceof AlarmAction) {
            smartHome.apply(action);
        } else {
            Action warnAlarmAction = new AlarmAction(AlarmAction.AlarmState.WARN, null);
            smartHome.apply(warnAlarmAction);
        }
    }
}
