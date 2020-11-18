package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.actions.ActionDecorator;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import static ru.sbt.mipt.oop.events.typedefs.EventType.*;

public class AlarmProcessor implements EventProcessor {
    private ActionHandler actionHandler;

    public AlarmProcessor(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public static boolean isAlarmEvent(EventType type) {
        return (type.equals(ALARM_ACTIVATE) || type.equals(ALARM_DEACTIVATE) || type.equals(ALARM_WARNING) || type.equals(GET_ALARM_STATE));
    }

    private Action getAlarmAction(Event event) {
        switch(event.getType()) {
            case ALARM_ACTIVATE:
                return new AlarmAction(AlarmAction.AlarmState.ACTIVATE, ((AlarmEvent) event).getActivationCode());
            case ALARM_DEACTIVATE:
                return new AlarmAction(AlarmAction.AlarmState.DEACTIVATE, ((AlarmEvent) event).getActivationCode());
            case ALARM_WARNING:
                return new AlarmAction(AlarmAction.AlarmState.WARN, null);
        }
        return null;
    }

    @Override
    public void processEvent(Event event) {
        if (isAlarmEvent(event.getType())) {
            actionHandler.apply(getAlarmAction(event));
        }
    }
}
