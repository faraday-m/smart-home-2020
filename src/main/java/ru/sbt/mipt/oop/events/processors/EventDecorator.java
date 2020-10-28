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
        fillProcessors();
    }

    private void fillProcessors() {
        processors.add(new DoorEventProcessor());
        processors.add(new LightEventProcessor());
        processors.add(new HallDoorEventProcessor());
        processors.add(new AlarmProcessor());
    }

    public void handleEvent(CCSensorEvent event) {
        Event eventAdapter = new SensorEventAdapter(event);
        GetAlarmStateEvent alarmStateEvent = new GetAlarmStateEvent();
        processors.forEach(p -> p.processEvent(smartHome, alarmStateEvent));
        if (alarmStateEvent.getState() == AlarmState.DEACTIVATED || eventAdapter.getType().isAlarmEvent()) {
            processors.forEach(p -> p.processEvent(smartHome, eventAdapter));
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
