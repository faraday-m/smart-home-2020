package ru.sbt.mipt.oop.elements.alarm;

import ru.sbt.mipt.oop.actions.Action;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.ElementType;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.HomeElementType;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.AlarmEventType;
import ru.sbt.mipt.oop.events.typedefs.DoorEventType;

import java.util.LinkedHashMap;
import java.util.Map;

public class AlarmSystem implements HomeComponent  {
    private ComponentId id;
    private int activationHashCode;
    private AlarmBehavior behavior;
    private Map<AlarmState, AlarmBehavior> behaviorMap;

    void setAlarmBehavior(AlarmState state) {
        if (!behaviorMap.containsKey(state)) {
            AlarmBehavior newBehavior;
            switch (state) {
                case DEACTIVATED:
                    newBehavior = new AlarmDeactivated(this);
                    break;
                case ACTIVATED:
                    newBehavior = new AlarmActivated(this);
                    break;
                case WARNING:
                    newBehavior = new AlarmWarning(this);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + state);
            }
            behaviorMap.put(state, newBehavior);
            this.behavior = newBehavior;
        } else {
            this.behavior = behaviorMap.get(state);
        }
    }

    public AlarmState getAlarmState() {
        return behavior.getState();
    }

    public boolean checkActivationCode(Object activationCode) {
        return (activationCode.hashCode() == activationHashCode);
    }
    public void setActivationCode(Object activationCode) {
        this.activationHashCode = activationCode.hashCode();
    }

    public AlarmSystem(ComponentId id) {
        this.id = id;
        this.behaviorMap = new LinkedHashMap<>();
        setAlarmBehavior(AlarmState.DEACTIVATED);
    }

    public AlarmSystem activate(Object activationCode) {
        behavior.activate(activationCode);
        return this;
    }

    public AlarmSystem deactivate(Object activationCode) {
        behavior.deactivate(activationCode);
        return this;
    }

    public AlarmSystem warn() {
        behavior.warn();
        return this;
    }

    @Override
    public ElementType getType() {
        return HomeElementType.ALARM;
    }

    @Override
    public ComponentId getId() {
        return id;
    }

    @Override
    public Event apply(Event event, Action action) {
        if (event.getType() instanceof AlarmEventType || event.getObjectId().equals(this.id)) {
            action.accept(this);
        }
        if (getAlarmState() == AlarmState.WARNING) {
            return new AlarmEvent(AlarmEventType.ALARM_WARNING, event.getObjectId(), ((AlarmEvent) event).getActivationCode());
        }
        return event;
    }
}
