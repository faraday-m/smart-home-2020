package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

@Configuration
public class EventManagerConfiguration {
    @Bean
    public SensorEventsManager getEventManager() {
        SensorEventsManager manager = new SensorEventsManager();
        manager.registerEventHandler(getEventDecorator());
        return manager;
    }

    @Bean
    public SmartHome getSmartHome(){
        try {
            return new JsonHomeLoader().load(new FileInputStream("smart-home-1.js"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Bean
    public EventDecorator getEventDecorator() {
        EventDecorator eventDecorator = new EventDecorator(getSmartHome());
        eventDecorator.registerEventProcessor(getAlarmProcessor());
        eventDecorator.registerEventProcessor(getDoorEventProcessor());
        eventDecorator.registerEventProcessor(getLightEventProcessor());
        eventDecorator.registerEventProcessor(getHallDoorEventProcessor());
        return eventDecorator;
    }
    
    @Bean
    public EventProcessor getAlarmProcessor() {
        return new AlarmProcessor();
    }
    
    @Bean
    public EventProcessor getDoorEventProcessor() {
        return new DoorEventProcessor();
    }
    @Bean
    public EventProcessor getLightEventProcessor() {
        return new LightEventProcessor();
    }
    @Bean
    public EventProcessor getHallDoorEventProcessor() {
        return new HallDoorEventProcessor();
    }
}
