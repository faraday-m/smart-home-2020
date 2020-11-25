package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.*;

import java.util.List;

public class AlarmNotifierDecorator implements EventProcessor {
    private List<? extends EventProcessor> processors;
    private AlarmSystem alarmSystem;

    public AlarmNotifierDecorator(List<? extends EventProcessor> eventProcessors, AlarmSystem alarmSystem) {
        this.processors = eventProcessors;
        this.alarmSystem = alarmSystem;
    }

    public void processEvent(Event event) {
        if (alarmSystem.isDeactivated()) {
            processors.forEach(p -> p.processEvent(event));
        } else {
            alarmSystem.warn();
            for (EventProcessor processor : processors) {
                if (processor instanceof AlarmProcessor) {
                    processor.processEvent(event);
                }
            }
        }
    }
}
