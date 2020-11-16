package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.Room;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class HomeBuilder {
    public static void main(String[] args) throws IOException {
        Room kitchen = RoomFactory.getKitchen();
        Room bathroom = RoomFactory.getBathroom();
        Room bedroom = RoomFactory.getBedroom();
        Room hall = RoomFactory.getHall();
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));
        HomeLoader homeRw = new JsonHomeLoader();
        try {
            homeRw.save(smartHome, new FileOutputStream("output.js"));
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to json");
        }
    }

}
