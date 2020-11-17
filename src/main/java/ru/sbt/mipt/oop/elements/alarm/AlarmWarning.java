package ru.sbt.mipt.oop.elements.alarm;

public class AlarmWarning implements AlarmBehavior {
    private final AlarmSystem system;

    AlarmWarning(AlarmSystem system) {
        this.system = system;
    }
    @Override
    public void activate(Object activationCode) {
        System.out.println("The system is in warning mode. First deactivate alarm with your actvation code");
    }

    @Override
    public void deactivate(Object providedCode) {
        if (system.checkActivationCode(providedCode)) {
            System.out.println("Alarm deactivated, end of warning");
            system.setAlarmBehavior(new AlarmDeactivated(system));
        } else {
            System.out.println("Invalid deactivation code");
        }
    }

    @Override
    public void warn() {
        system.sendSms("Trespassing!");
        System.out.println("Alarm is already in warning mode");
    }
}
