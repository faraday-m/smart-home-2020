package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.events.Event;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.typedefs.EventType.LIGHTS_OFF;

public class SmartHome implements HomeComponent {
    private Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public ElementType getType() {
        return HomeElementType.SMART_HOME;
    }

    @Override
    public ComponentId getId() {
        return new StringId("HOME");
    }

    public void apply(Event event, Action action) {
        rooms.forEach((Room r) -> r.apply(event, action));
    }
}
