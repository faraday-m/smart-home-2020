package ru.sbt.mipt.oop.remotecontrol;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class EventManagerTestConfiguration {
    @Bean
    public SensorEventsManager getEventManager() {
        SensorEventsManager manager = new SensorEventsManager();
        manager.registerEventHandler(getEventDecorator());
        return manager;
    }
    
    @Bean(name="kitchenLights")
    public Map<ComponentId, Light> getKitchenLights() {
        Map<ComponentId, Light> kitchenTestLights = new LinkedHashMap<>();
        kitchenTestLights.put(new StringId("1"), new Light("1", false));
        kitchenTestLights.put(new StringId("2"), new Light("2", true));
        return kitchenTestLights;
    }
    
    @Bean(name="hallLights")
    public Map<ComponentId, Light> getHallLights() {
        Map<ComponentId, Light> hallTestLights = new LinkedHashMap<>();
        hallTestLights.put(new StringId("3"), new Light("3", true));
        return hallTestLights;
    }
    
    @Bean(name="hallDoors")
    public Map<ComponentId, Door> getHallDoors() {
        Map<ComponentId, Door> testDoors = new LinkedHashMap<>();
        testDoors.put(new StringId("1"), new Door("1", true));
        return testDoors;
    }
    @Bean
    public SmartHome getSmartHome(){
        Room kitchen = new Room(getKitchenLights(), new LinkedHashMap<>(), "kitchen");
        Room hall = new Room(getHallLights(), getHallDoors(), "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, hall));
        smartHome.setAlarmSystem(getAlarmSystem());
        return smartHome;
    }
    
    @Bean
    public AlarmSystem getAlarmSystem() {
        return new AlarmSystem();
    }
    
    
    @Bean
    public EventDecorator getEventDecorator() {
        EventDecorator eventDecorator = new EventDecorator(getSmartHome());
        eventDecorator.registerEventProcessor(getAlarmProcessor());
        eventDecorator.registerEventProcessor(getDoorEventProcessor());
        eventDecorator.registerEventProcessor(getLightEventProcessor());
        eventDecorator.registerEventProcessor(getHomeEventProcessor());
        eventDecorator.registerEventProcessor(getRoomEventProcessor());
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
    public EventProcessor getRoomEventProcessor() {
        return new HomeEventProcessor();
    }
    
    @Bean
    public EventProcessor getHomeEventProcessor() {
        return new RoomEventProcessor();
    }
}
