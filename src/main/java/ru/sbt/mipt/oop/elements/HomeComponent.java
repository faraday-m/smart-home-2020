package ru.sbt.mipt.oop.elements;


public interface HomeComponent extends Actionable {
    ElementType getType();
    ComponentId getId();
}
