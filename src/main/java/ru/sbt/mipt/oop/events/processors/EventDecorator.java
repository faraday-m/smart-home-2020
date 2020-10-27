package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.GetAlarmStateEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.ArrayList;
import java.util.List;

public class EventDecorator {
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

    public void processEvent(Event event) {
        GetAlarmStateEvent alarmStateEvent = new GetAlarmStateEvent();
        processors.forEach(p -> p.processEvent(smartHome, alarmStateEvent));
        if (alarmStateEvent.getState() != AlarmState.ACTIVATED || event.getType().isAlarmEvent()) {
            processors.forEach(p -> p.processEvent(smartHome, event));
        } else {
            Event alarmWarning = new AlarmEvent(EventType.ALARM_WARNING, new StringId("ALARM"), null);
            processors.forEach(p -> p.processEvent(smartHome, alarmWarning));
            sendSms("Trespassing!");
        }
    }

    private void sendSms(String sms) {
        System.out.println(String.format("Sending sms: %s", sms));
    }
}
