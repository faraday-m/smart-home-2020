package ru.sbt.mipt.oop.remotecontrol;

import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.commands.SMSNotifier;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.processors.*;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class EventManagerTestConfiguration {
    @Autowired
    List<EventProcessor> processors;

    @Bean
    public SensorEventsManager eventManager() {
        SensorEventsManager manager = new SensorEventsManager();
        manager.registerEventHandler(eventDecorator());
        return manager;
    }
    
    @Bean(name="kitchenLights")
    public Map<ComponentId, Light> kitchenLights() {
        Map<ComponentId, Light> kitchenTestLights = new LinkedHashMap<>();
        kitchenTestLights.put(new StringId("1"), new Light("1", false));
        kitchenTestLights.put(new StringId("2"), new Light("2", true));
        return kitchenTestLights;
    }
    
    @Bean(name="hallLights")
    public Map<ComponentId, Light> hallLights() {
        Map<ComponentId, Light> hallTestLights = new LinkedHashMap<>();
        hallTestLights.put(new StringId("3"), new Light("3", true));
        return hallTestLights;
    }
    
    @Bean(name="hallDoors")
    public Map<ComponentId, Door> hallDoors() {
        Map<ComponentId, Door> testDoors = new LinkedHashMap<>();
        testDoors.put(new StringId("1"), new Door("1", true));
        return testDoors;
    }
    @Bean
    public SmartHome smartHome(){
        Room kitchen = new Room(kitchenLights(), new LinkedHashMap<>(), "kitchen");
        Room hall = new Room(hallLights(), hallDoors(), "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, hall));
        smartHome.setAlarmSystem(alarmSystem());
        return smartHome;
    }
    
    @Bean
    public AlarmSystem alarmSystem() {
        return new AlarmSystem(smsNotifier());
    }

    @Bean
    public SMSNotifier smsNotifier() {
        return new SMSNotifier();
    }
    
    @Bean
    public EventDecorator eventDecorator() {
        return new EventDecorator(smartHome(), alarmSystem(), processors);
    }
    
    @Bean
    public EventProcessor alarmProcessor() {
        return new AlarmProcessor(smartHome());
    }
    
    @Bean
    public EventProcessor doorEventProcessor() {
        return new DoorEventProcessor(smartHome());
    }
    
    @Bean
    public EventProcessor lightEventProcessor() {
        return new LightEventProcessor(smartHome());
    }
}
