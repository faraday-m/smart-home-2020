package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.HallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.sbt.mipt.oop.events.typedefs.EventType.*;

public class Room implements HomeComponent, HomeComponentComposite {

    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    @Override
    public void addHomeComponent(ElementType type, HomeComponent component) {
        if (type.equals(HomeElementType.DOOR)) {
            doors.add((Door)component);
        } else if (type.equals(HomeElementType.LIGHT)) {
            lights.add((Light)component);
        }
    }

    @Override
    public Collection<? extends HomeComponent> getComponents(Predicate<? super HomeComponent> condition) {
        List<HomeComponent> resultList = doors.stream().filter(condition).collect(Collectors.toList());
        List<HomeComponent> lightList = lights.stream().filter(condition).collect(Collectors.toList());
        resultList.addAll(lightList);
        return resultList;
    }

    public Room(Map<ComponentId, Light> lights, Map<ComponentId, Door> doors, String name) {
        this.lights = lights.values();
        this.doors = doors.values();
        this.name = name;
    }

    public HomeComponent getComponent(Predicate<? super HomeComponent> condition) {
        HomeComponent door = doors.stream().filter(condition/*(HomeComponent c) -> c.getId().equals(id)*/).findFirst().orElse(null);
        if (door != null) {
            return door;
        }
        HomeComponent light = lights.stream().filter(condition).findFirst().orElse(null);
        return light;
    }

    public int getElementCount(ElementType type) {
        if (type == HomeElementType.DOOR) {
            return ( doors == null ? 0 : doors.size());
        }
        if (type == HomeElementType.LIGHT) {
            return ( lights == null ? 0 : lights.size());
        }
        return 0;
    }

    public String getName() {
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
    public Event apply(Event event, Action action) {
        Event inputEvent = event;
        if (inputEvent.getType() == EventType.DOOR_OPEN || inputEvent.getType() == EventType.DOOR_CLOSED) {
            Collection<Door> doorsWithId = doors.stream()
                    .filter((HomeComponent c) -> (c.getId().equals(inputEvent.getObjectId())))
                    .collect(Collectors.toList());
            boolean hasDoor = (doorsWithId.size() > 0);
            if (hasDoor) {
                doorsWithId.forEach((Door d) -> d.apply(inputEvent, action));
                if (this.name.equals("hall") && (event.getType() == EventType.DOOR_CLOSED)) {
                    event = new HallDoorEvent(LIGHTS_OFF, this.getId(), new SimpleSensorCommand(CommandType.LIGHT_OFF, getId()));
                }
            }
        } else if (event.getType() == LIGHT_OFF || event.getType() == LIGHT_ON) {
            lights.stream().filter((HomeComponent c) -> (c.getId().equals(inputEvent.getObjectId()))).forEach(action);
        } else if (event.getType() == LIGHTS_OFF) {
            lights.forEach((HomeComponent c) -> c.apply(inputEvent, action));
        }
        return event;
    }
}
