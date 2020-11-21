package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.*;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_OPEN;

public class DoorEventProcessor extends HomeEventProcessor {

    public DoorEventProcessor(ActionHandler actionHandler) {
        this.actionHandler = actionHandler;
    }

    public static boolean isDoorEvent(Event event) {
        return (event.getType().equals(DOOR_OPEN) || event.getType().equals(DOOR_CLOSED));
    }

    public void processEvent(Event event) {
        if (isDoorEvent(event)) {
            actionHandler.apply(new DoorAction(event.getObjectId(), event.getType() == DOOR_OPEN));
            if (event.getType() == DOOR_CLOSED) {
                GetHallDoorAction getHallDoor = new GetHallDoorAction();
                actionHandler.apply(getHallDoor); //Получаем айди двери в холле
                if (getHallDoor.getDoorId() != null && event.getObjectId().equals(getHallDoor.getDoorId())) {
                    actionHandler.apply(new LightsAction(false));
                }
            }
        }
    }
}
