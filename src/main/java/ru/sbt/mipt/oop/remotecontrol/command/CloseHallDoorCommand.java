package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.DoorAction;
import ru.sbt.mipt.oop.actions.GetHallDoorAction;
import ru.sbt.mipt.oop.actions.LightsAction;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.elements.SmartHome;

public class CloseHallDoorCommand extends AbstractRemoteControlCommand {
  public CloseHallDoorCommand(ActionHandler actionHandler) {
    super(actionHandler);
  }

  @Override
  public void execute() {
    GetHallDoorAction getHallDoorAction = new GetHallDoorAction();
    actionHandler.apply(getHallDoorAction);
    ComponentId doorId = getHallDoorAction.getDoorId();
    if (doorId != null) {
      actionHandler.apply(new DoorAction(doorId, false));
      actionHandler.apply(new LightsAction(false));
    }
  }
}
