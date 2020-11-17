package ru.sbt.mipt.oop.elements.alarm;

public class AlarmActivated implements AlarmBehavior {
    private final AlarmSystem system;

    AlarmActivated(AlarmSystem system) {
        this.system = system;
    }

    @Override
    public void activate(Object activationCode) {
        System.out.println("Alarm is already activated");
    }

    @Override
    public void deactivate(Object providedCode) {
        if (system.checkActivationCode(providedCode)) {
            System.out.println("Alarm deactivated");
            system.setAlarmBehavior(new AlarmDeactivated(system));
        } else {
            this.warn();
        }
    }

    @Override
    public void warn() {
        System.out.println("Turning warning mode on!");
        system.sendSms("Trespassing!");
        system.setAlarmBehavior(new AlarmWarning(system));
    }
}
