package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.actions.DoorAction;
import ru.sbt.mipt.oop.actions.GetHallDoorAction;
import ru.sbt.mipt.oop.actions.LightsAction;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;

import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.events.typedefs.EventType.DOOR_OPEN;

public class DoorEventProcessor implements EventProcessor {
    private SmartHome smartHome;

    public DoorEventProcessor(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    public static boolean isDoorEvent(Event event) {
        return (event.getType().equals(DOOR_OPEN) || event.getType().equals(DOOR_CLOSED));
    }

    public void processEvent(Event event) {
        if (isDoorEvent(event)) {
            smartHome.apply(new DoorAction(event.getObjectId(), event.getType() == DOOR_OPEN));
            if (event.getType() == DOOR_CLOSED) {
                GetHallDoorAction getHallDoor = new GetHallDoorAction();
                smartHome.apply(getHallDoor); //Получаем айди двери в холле
                if (event.getObjectId().equals(getHallDoor.getDoorId())) {
                    smartHome.apply(new LightsAction(false));
                }
            }
        }
    }
}
