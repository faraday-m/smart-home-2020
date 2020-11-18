package ru.sbt.mipt.oop.configuration;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.actions.ActionDecorator;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.commands.Notifier;
import ru.sbt.mipt.oop.commands.SMSNotifier;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.processors.*;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.util.List;

@Configuration
public class EventManagerConfiguration {
    @Autowired
    List<EventProcessor> processors;

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
    public EventDecorator eventDecorator() {
        return new EventDecorator(processors);
    }

    @Bean
    public EventHandler eventHandler() {
        return new EventHandlerAdapter(eventDecorator());
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
    public ActionHandler actionHandler() {
        return new ActionDecorator(smartHome(), alarmSystem());
    }

    @Bean
    public EventProcessor alarmProcessor() {
        return new AlarmProcessor(actionHandler());
    }
    
    @Bean
    public EventProcessor doorEventProcessor() {
        return new DoorEventProcessor(actionHandler());
    }
    
    @Bean
    public EventProcessor lightEventProcessor() {
        return new LightEventProcessor(actionHandler());
    }

    
}
