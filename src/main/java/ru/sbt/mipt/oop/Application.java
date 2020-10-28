package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import ru.sbt.mipt.oop.configuration.EventManagerConfiguration;

public class Application {
    public static void main(String... args) {
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(EventManagerConfiguration.class);
        SensorEventsManager eventManager = context.getBean(SensorEventsManager.class);
        eventManager.start();
    }

}
