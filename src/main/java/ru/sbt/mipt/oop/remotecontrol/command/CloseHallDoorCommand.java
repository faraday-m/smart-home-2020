package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.events.DoorEvent;
import ru.sbt.mipt.oop.events.GetHallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class CloseHallDoorCommand extends AbstractRemoteControlCommand {
  @Override
  public void execute() {
    GetHallDoorEvent hallDoorEvent = new GetHallDoorEvent();
    eventDecorator.handleEvent(hallDoorEvent);
    ComponentId doorId = hallDoorEvent.getObjectId();
    eventDecorator.handleEvent(new DoorEvent(EventType.DOOR_CLOSED, doorId));
  }
}
