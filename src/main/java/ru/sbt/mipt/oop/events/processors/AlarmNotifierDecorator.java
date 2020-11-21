package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.*;

import java.util.List;

public class AlarmNotifierDecorator implements EventProcessor{
    private List<? extends EventProcessor> processors;
    private AlarmSystem alarmSystem;
    private ActionHandler actionHandler;

    public AlarmNotifierDecorator(List<? extends EventProcessor> eventProcessors, AlarmSystem alarmSystem, ActionHandler actionHandler) {
        this.processors = eventProcessors;
        this.alarmSystem = alarmSystem;
        this.actionHandler = actionHandler;
    }

    public void processEvent(Event event) {
        if (alarmSystem.isDeactivated() || event instanceof AlarmEvent) {
            processors.forEach(p -> p.processEvent(event));
        } else {
            Action warnAlarmAction = new AlarmAction(AlarmAction.AlarmState.WARN, null);
            actionHandler.apply(warnAlarmAction);
        }
    }
}
