package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.Door;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.Room;

public class RoomFactory {
    public static Room getKitchen() {
        return new Room.RoomBuilder()
                .withName("kitchen")
                .withLights(new Light("1", false), new Light("2", true))
                .withDoors(new Door("1", false))
                .build();
    }

    public static Room getBathroom() {
        return new Room.RoomBuilder()
                .withName("bathroom")
                .withLights(new Light("3", true))
                .withDoors(new Door("2", false))
                .build();

    }

    public static Room getBedroom() {
        return new Room.RoomBuilder()
                .withName("bedroom")
                .withLights(new Light("4", false), new Light("5", false), new Light("6", false))
                .withDoors(new Door("3", true))
                .build();
    }

    public static Room getHall() {
        return new Room.RoomBuilder()
                .withName("hall")
                .withLights(new Light("7", false), new Light("8", false), new Light("9", false))
                .withDoors(new Door("4", false))
                .build();
    }
}
