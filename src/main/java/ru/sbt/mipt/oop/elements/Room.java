package ru.sbt.mipt.oop.elements;

import ru.sbt.mipt.oop.actions.*;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

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
    public ComponentId getId() {
        return new StringId(name);
    }

    @Override
    public void apply(Action action) {
        if (action instanceof DoorAction) {
            doors.stream()
                .filter((HomeComponent c) -> (c.getId().equals(((DoorAction) action).getId())))
                .forEach((Door d) -> d.apply(action));
        } else if (action instanceof LightAction) {
            lights.stream().filter((HomeComponent c) -> (c.getId().equals(((LightAction) action).getId()))).forEach(action);
        } else if (action instanceof GetHallDoorAction) {
            if (this.getName().equals("hall")) {
                doors.stream().findFirst().get().apply(action);
            }
        } else if (action instanceof LightsAction) {
            lights.forEach((HomeComponent c) -> c.apply(action));
        } else if (action instanceof RoomLightsAction) {
            if (this.getName().equals(((RoomLightsAction) action).getRoomName().toString())) {
                lights.forEach((HomeComponent c) -> c.apply(action));
            }
        }
    }
}
