package ru.sbt.mipt.oop.actions;

import ru.sbt.mipt.oop.elements.HomeComponent;

import java.util.function.Consumer;
@FunctionalInterface
public interface Action extends Consumer<HomeComponent> {
}
