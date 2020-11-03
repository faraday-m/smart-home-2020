package ru.sbt.mipt.oop.elements.alarm;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.events.Event;

public class AlarmSystem implements HomeComponent  {
    private final ComponentId id;
    private int activationHashCode;
    private AlarmBehavior behavior;

    void setAlarmBehavior(AlarmBehavior behavior) {
            this.behavior = behavior;
    }

    public AlarmState getAlarmState() {
        return behavior.getState();
    }

    boolean checkActivationCode(Object activationCode) {
        return (activationCode.hashCode() == activationHashCode);
    }

    void setActivationCode(Object activationCode) {
        this.activationHashCode = activationCode.hashCode();
    }

    public AlarmSystem() {
        this.id = new StringId("ALARM");
        setAlarmBehavior(new AlarmDeactivated(this));
    }

    public void activate(Object activationCode) {
        behavior.activate(activationCode);
    }

    public void deactivate(Object activationCode) {
        behavior.deactivate(activationCode);
    }

    public void warn() {
        behavior.warn();
    }

    @Override
    public ElementType getType() {
        return HomeElementType.ALARM;
    }

    @Override
    public ComponentId getId() {
        return id;
    }

    @Override
    public void apply(Event event, Action action) {
        if (event.getObjectId().equals(this.id)) {
            action.accept(this);
        }
    }
}
