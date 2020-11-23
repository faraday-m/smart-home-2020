package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.sbt.mipt.oop.actions.CompositeActionHandler;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.commands.Notifier;
import ru.sbt.mipt.oop.commands.SMSNotifier;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.events.typedefs.EventType;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class EventManagerConfiguration {
    @Autowired
    List<HomeEventProcessor> processors;

    @Bean
    public SensorEventsManager eventManager() {
        SensorEventsManager manager = new SensorEventsManager();
        manager.registerEventHandler(eventHandler());
        return manager;
    }

    @Bean
    public SmartHome smartHome(){
        try {
            SmartHome home = new JsonHomeLoader().load(new FileInputStream("smart-home-1.js"));
            home.setAlarmSystem(alarmSystem());
            return home;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Bean
    public EventProcessor eventDecorator() {
        return new AlarmNotifierDecorator(processors, alarmSystem());
    }

    @Bean
    public EventHandler eventHandler() {
        return new EventHandlerTransformer(eventDecorator(), eventTypeMap());
    }

    @Bean
    public AlarmSystem alarmSystem() {
        return new AlarmSystem(smsNotifier());
    }

    @Bean
    public Notifier smsNotifier() {
        return new SMSNotifier();
    }

    @Bean
    public Map<String, EventType> eventTypeMap() {
        Map<String, EventType> eventTypeMap = new LinkedHashMap<>();
        eventTypeMap.put("LightIsOn", EventType.LIGHT_ON);
        eventTypeMap.put("LightIsOff", EventType.LIGHT_OFF);
        eventTypeMap.put("DoorIsOpen", EventType.DOOR_OPEN);
        eventTypeMap.put("DoorIsClosed", EventType.DOOR_CLOSED);
        eventTypeMap.put("DoorIsLocked", EventType.ALARM_ACTIVATE);
        eventTypeMap.put("DoorIsUnlocked", EventType.ALARM_DEACTIVATE);
        return eventTypeMap;
    }

    @Bean
    public ActionHandler actionHandler() {
        return new CompositeActionHandler(smartHome(), alarmSystem());
    }

    @Bean
    public HomeEventProcessor alarmProcessor() {
        return new AlarmProcessor(actionHandler());
    }
    
    @Bean
    public HomeEventProcessor doorEventProcessor() {
        return new DoorEventProcessor(actionHandler());
    }
    
    @Bean
    public HomeEventProcessor lightEventProcessor() {
        return new LightEventProcessor(actionHandler());
    }

    
}
