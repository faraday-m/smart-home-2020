package ru.sbt.mipt.oop.elements.alarm;

public class AlarmDeactivated implements AlarmBehavior {
    private final AlarmSystem system;

    AlarmDeactivated(AlarmSystem system) {
        this.system = system;
    }

    @Override
    public void activate(Object activationCode) {
        system.setActivationCode(activationCode);
        system.setAlarmBehavior(new AlarmActivated(system));
        System.out.println("Alarm is activated");
    }

    @Override
    public void deactivate(Object providedCode) {
        System.out.println("Alarm is already deactivated");
    }

    @Override
    public void warn() {
        System.out.println("Turning warning mode on!");
        system.setAlarmBehavior(new AlarmWarning(system));
    }
}
