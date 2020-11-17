package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.HomeComponent;

public class GetAlarmAction implements Action {
    private HomeComponent alarm;

    public HomeComponent getAlarm() {
        return alarm;
    }

    @Override
    public void accept(HomeComponent component) {
        this.alarm = component;
    }
}
