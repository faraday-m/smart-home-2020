package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.processors.EventDecorator;
import ru.sbt.mipt.oop.events.processors.SensorEventGenerator;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class Application {
    private static HomeLoader homeLoader;
    public static String ACTIVATION_CODE_1 = "code1";
    public static String ACTIVATION_CODE_2 = "code2";

    public Application(HomeLoader homeLoader) {
        this.homeLoader = homeLoader;
    }

    public static void main(String... args) throws IOException {
        HomeLoader homeLoader = new JsonHomeLoader();
        Application application = new Application(homeLoader);
        application.run();
    }


    private void run() {
        // считываем состояние дома из файла
        try {
            SmartHome smartHome = homeLoader.load(new FileInputStream("smart-home-1.js"));
            Engine engine = new SmartHomeEngine(new EventDecorator(smartHome), new SensorEventGenerator(smartHome));
            engine.start();

        } catch (IOException e) {
            System.out.println("Error while loading from JSON");
        }
    }


}
