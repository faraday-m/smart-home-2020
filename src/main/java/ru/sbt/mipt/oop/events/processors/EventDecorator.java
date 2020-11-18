package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.actions.GetAlarmAction;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.elements.alarm.AlarmActivated;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.List;

public class EventDecorator implements EventProcessor {
    private List<EventProcessor> processors;
    private SmartHome smartHome;
    private AlarmSystem alarm;

    public EventDecorator(SmartHome smartHome, AlarmSystem alarm, List<EventProcessor> eventProcessors) {
        this.smartHome = smartHome;
        this.alarm = alarm;
        this.processors = eventProcessors;
    }

    public void processEvent(Event event) {
        if (alarm.isDeactivated() || AlarmProcessor.isAlarmEvent(event)) {
            processors.forEach(p -> p.processEvent(event));
        } else {
            Action warnAlarmAction = new AlarmAction(AlarmAction.AlarmState.WARN, null);
            smartHome.apply(warnAlarmAction);
        }
    }
}
