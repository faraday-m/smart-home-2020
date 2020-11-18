package ru.sbt.mipt.oop.elements.alarm;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.commands.Notifier;
import ru.sbt.mipt.oop.elements.*;

public class AlarmSystem implements HomeComponent {
    private final ComponentId id;
    private int activationHashCode;
    private AlarmBehavior behavior;
    private Notifier notifier;

    void setAlarmBehavior(AlarmBehavior behavior) {
            this.behavior = behavior;
    }

    boolean checkActivationCode(Object activationCode) {
        return (activationCode.hashCode() == activationHashCode);
    }

    void setActivationCode(Object activationCode) {
        this.activationHashCode = activationCode.hashCode();
    }

    public AlarmSystem(Notifier notifier) {
        this.id = new StringId("ALARM");
        this.notifier = notifier;
        setAlarmBehavior(new AlarmDeactivated(this));
    }

    public void sendNotification(String message) {
        notifier.sendNotification(message);
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
    public ComponentId getId() {
        return id;
    }

    @Override
    public void apply(Action action) {
        action.accept(this);
    }

    public boolean isActivated() {
        return (behavior instanceof AlarmActivated);
    }

    public boolean isDeactivated() {
        return (behavior instanceof AlarmDeactivated);
    }

    public boolean isWarned() {
        return (behavior instanceof AlarmWarning);
    }
}
