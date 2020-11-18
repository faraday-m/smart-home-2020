package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.GetHallDoorAction;
import ru.sbt.mipt.oop.actions.LightsAction;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {
    private SmartHome smartHome;

    public HallDoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public void processEvent(Event event) {
        GetHallDoorAction getHallDoorAction = new GetHallDoorAction();
        smartHome.apply(getHallDoorAction);
        if (event.getType() == DOOR_CLOSED && event.getObjectId().equals(getHallDoorAction.getDoorId())) {
            smartHome.apply(new LightsAction(false));
        }
    }
}
