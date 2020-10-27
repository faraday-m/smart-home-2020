package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.GetHallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import static ru.sbt.mipt.oop.events.typedefs.EventType.*;

public class Room implements HomeComponent {

    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Map<ComponentId, Light> lights, Map<ComponentId, Door> doors, String name) {
        this.lights = lights.values();
        this.doors = doors.values();
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    public ElementType getType() {
        return HomeElementType.ROOM;
    }

    @Override
    public ComponentId getId() {
        return new StringId("Room " + name);
    }

    @Override
    public void apply(Event event, Action action) {
        Event inputEvent = event;
        if (inputEvent.getType() == EventType.DOOR_OPEN || inputEvent.getType() == EventType.DOOR_CLOSED) {
            Collection<Door> doorsWithId = doors.stream()
                    .filter((HomeComponent c) -> (c.getId().equals(inputEvent.getObjectId())))
                    .collect(Collectors.toList());
            boolean hasDoor = (doorsWithId.size() > 0);
            if (hasDoor) {
                doorsWithId.forEach((Door d) -> d.apply(inputEvent, action));
            }
        } else if (event.getType() == LIGHT_OFF || event.getType() == LIGHT_ON) {
            lights.stream().filter((HomeComponent c) -> (c.getId().equals(inputEvent.getObjectId()))).forEach(action);
        } else if (event.getType() == GET_HALLDOOR) {
            if (this.getName().equals("hall")) {
                ((GetHallDoorEvent) event).setObjectId(doors.stream().findFirst().get().getId());
            }
        } else if (event.getType() == LIGHTS_OFF) {
            lights.forEach((HomeComponent c) -> c.apply(inputEvent, action));
        }
    }
}
