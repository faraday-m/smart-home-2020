package ru.sbt.mipt.oop.elements.alarm;

public interface AlarmBehavior {
    void activate(Object activationCode);
    void deactivate(Object providedCode);
    void warn();
}
