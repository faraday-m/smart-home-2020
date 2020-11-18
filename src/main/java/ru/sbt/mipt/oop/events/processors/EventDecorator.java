package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.AlarmAction;
import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.*;

import java.util.List;

public class EventDecorator implements EventHandler {
    private List<EventProcessor> processors;
    private SmartHome smartHome;
    private AlarmSystem alarm;

    public EventDecorator(SmartHome smartHome, AlarmSystem alarm, List<EventProcessor> eventProcessors) {
        this.smartHome = smartHome;
        this.alarm = alarm;
        this.processors = eventProcessors;
    }

    public void registerEventProcessor(EventProcessor processor) {
        processors.add(processor);
    }

    public void handleEvent(CCSensorEvent event) {
        Event eventAdapter = new SensorEventAdapter(event);
        processEvent(eventAdapter);
    }

    public void processEvent(Event event) {
        if (alarm.isDeactivated() || AlarmProcessor.isAlarmEvent(event.getType())) {
            processors.forEach(p -> p.processEvent(event));
        } else {
            Action warnAlarmAction = new AlarmAction(AlarmAction.AlarmState.WARN, null);
            smartHome.apply(warnAlarmAction);
        }
    }
}
