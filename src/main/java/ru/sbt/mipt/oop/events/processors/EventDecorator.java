package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.actions.GetAlarmAction;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.elements.alarm.AlarmActivated;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.List;

public class EventDecorator implements EventProcessor {
    private List<EventProcessor> processors;
    private SmartHome smartHome;

    public EventDecorator(SmartHome smartHome, List<EventProcessor> eventProcessors) {
        this.smartHome = smartHome;
        this.processors = eventProcessors;
    }

    public void processEvent(Event event) {
        GetAlarmAction alarmAction = new GetAlarmAction();
        smartHome.apply(alarmAction);
        if (!(alarmAction.getAlarm() instanceof AlarmActivated) || AlarmProcessor.isAlarmEvent(event)) {
            processors.forEach(p -> p.processEvent(event));
        } else {
            Action warnAlarmAction = new AlarmAction(AlarmAction.AlarmState.WARN, null);
            smartHome.apply(warnAlarmAction);
        }
    }
}
