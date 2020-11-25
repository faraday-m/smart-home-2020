package ru.sbt.mipt.oop.elements;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class RoomFactory {
    public static Room getKitchen() {
        Map<ComponentId, Light> lights = new LinkedHashMap<>();
        Map<ComponentId, Door> doors = new LinkedHashMap<>();
        lights.put(new StringId("1"), new Light("1", false));
        lights.put(new StringId("2"), new Light("2", true));
        doors.put(new StringId("1"), new Door("1", false));
        return new Room(lights, doors, "kitchen");
    }

    public static Room getBathroom() {
        Map<ComponentId, Light> lights = new LinkedHashMap<>();
        Map<ComponentId, Door> doors = new LinkedHashMap<>();
        lights.put(new StringId("3"), new Light("3", true));
        doors.put(new StringId("2"), new Door("2", false));
        return new Room(lights, doors, "bathroom");

    }

    public static Room getBedroom() {
        Map<ComponentId, Light> lights = new LinkedHashMap<>();
        Map<ComponentId, Door> doors = new LinkedHashMap<>();
        lights.put(new StringId("4"), new Light("4", false));
        lights.put(new StringId("5"), new Light("5", false));
        lights.put(new StringId("6"), new Light("6", false));
        doors.put(new StringId("3"), new Door("3", true));
        return new Room(lights, doors,"bedroom");
    }

    public static Room getHall() {
        Map<ComponentId, Light> lights = new LinkedHashMap<>();
        Map<ComponentId, Door> doors = new LinkedHashMap<>();
        lights.put(new StringId("7"), new Light("7", false));
        lights.put(new StringId("8"), new Light("8", false));
        lights.put(new StringId("9"), new Light("9", false));
        doors.put(new StringId("4"), new Door("4", false));
        return new Room(lights, doors,"hall");
    }
}
