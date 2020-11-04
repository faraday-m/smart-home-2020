package ru.sbt.mipt.oop.events.processors;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.ArrayList;
import java.util.List;

public class EventDecorator implements EventHandler {
    private List<EventProcessor> processors;
    private SmartHome smartHome;

    public EventDecorator(SmartHome smartHome) {
        this.smartHome = smartHome;
        this.processors = new ArrayList<>();
    }
    
    public void registerEventProcessor(EventProcessor processor) {
        processors.add(processor);
    }

    public void handleEvent(CCSensorEvent event) {
        Event eventAdapter = new SensorEventAdapter(event);
        handleEvent(eventAdapter);
    }
    
    
    public void handleEvent(Event event) {
        GetAlarmStateEvent alarmStateEvent = new GetAlarmStateEvent();
        processors.forEach(p -> p.processEvent(smartHome, alarmStateEvent));
        if (alarmStateEvent.getState() == AlarmState.DEACTIVATED || event.getType().isAlarmEvent()) {
            processors.forEach(p -> p.processEvent(smartHome, event));
        } else {
            if (alarmStateEvent.getState() == AlarmState.ACTIVATED) {
                sendSms("Trespassing!");
                Event alarmWarning = new AlarmEvent(EventType.ALARM_WARNING, new StringId("ALARM"), null);
                processors.forEach(p -> p.processEvent(smartHome, alarmWarning));
            }
        }
    }

    private void sendSms(String sms) {
        System.out.println(String.format("Sending sms: %s", sms));
    }
}
