package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.LightAction;
import ru.sbt.mipt.oop.elements.HomeComponent;
import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.LightEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import static ru.sbt.mipt.oop.events.typedefs.EventType.*;

public class LightEventProcessor extends HomeEventProcessor {
    public LightEventProcessor(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public static boolean isLightEvent(Event event) {
        return (event.getType().equals(LIGHT_OFF) || event.getType().equals(LIGHT_ON));
    }

    public void processEvent(Event event) {
        if (isLightEvent(event)) {
            actionHandler.apply(new LightAction(event.getObjectId(),event.getType() == LIGHT_ON));
        }
    }
}
