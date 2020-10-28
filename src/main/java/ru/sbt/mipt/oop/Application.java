package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.configuration.EventManagerConfiguration;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.processors.EventDecorator;
import ru.sbt.mipt.oop.events.processors.SensorEventGenerator;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.IOException;

public class Application {
    public static void main(String... args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(EventManagerConfiguration.class);
        SensorEventsManager eventManager = context.getBean(SensorEventsManager.class);
        eventManager.start();
    }

}
