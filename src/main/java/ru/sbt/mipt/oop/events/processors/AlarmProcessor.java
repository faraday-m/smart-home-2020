package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.GetAlarmStateEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class AlarmProcessor implements EventProcessor {
    @Override
    public void processEvent(SmartHome smartHome, Event event) {
        if (event.getType().isAlarmEvent()) {
            smartHome.apply(event, ((HomeComponent system) -> {
                if (event.getType() == EventType.ALARM_ACTIVATE) {
                    ((AlarmSystem) system).activate(((AlarmEvent) event).getActivationCode());
                } else if (event.getType() == EventType.ALARM_DEACTIVATE) {
                    ((AlarmSystem) system).deactivate(((AlarmEvent) event).getActivationCode());
                } else if (event.getType() == EventType.ALARM_WARNING) {
                    ((AlarmSystem) system).warn();
                } else if (event.getType() == EventType.GET_ALARM_STATE) {
                    ((GetAlarmStateEvent)event).setState(((AlarmSystem) system).getAlarmState());
                }
            }));
        }
    }
}
