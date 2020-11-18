package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;

public class AlarmAction implements Action {
    public enum AlarmState { ACTIVATE, DEACTIVATE, WARN };

    private Object activationCode;
    private AlarmState state;

    public AlarmAction(AlarmState state, Object activationCode) {
        this.state = state;
        this.activationCode = activationCode;
    }

    @Override
    public void accept(HomeComponent component) {
        if (component instanceof AlarmSystem) {
            switch (state) {
                case WARN:
                    ((AlarmSystem) component).warn();
                    break;
                case ACTIVATE:
                    ((AlarmSystem) component).activate(activationCode);
                    break;
                case DEACTIVATE:
                    ((AlarmSystem) component).deactivate(activationCode);
                    break;
            }
        }
    }
}
