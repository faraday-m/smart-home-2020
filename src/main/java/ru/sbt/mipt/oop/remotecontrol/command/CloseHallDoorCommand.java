package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.DoorAction;
import ru.sbt.mipt.oop.actions.GetHallDoorAction;
import ru.sbt.mipt.oop.actions.LightsAction;
import ru.sbt.mipt.oop.elements.ComponentId;

public class CloseHallDoorCommand extends AbstractRemoteControlCommand {
  @Override
  public void execute() {
    GetHallDoorAction getHallDoorAction = new GetHallDoorAction();
    smartHome.apply(getHallDoorAction);
    ComponentId doorId = getHallDoorAction.getDoorId();
    smartHome.apply(new DoorAction(doorId, false));
    smartHome.apply(new LightsAction(false));
  }
}
