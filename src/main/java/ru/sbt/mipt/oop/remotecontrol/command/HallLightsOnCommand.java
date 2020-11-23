package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.RoomLightsAction;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;

public class HallLightsOnCommand implements RemoteControlCommand {
  private ActionHandler actionHandler;

  public HallLightsOnCommand(ActionHandler actionHandler) {
    this.actionHandler = actionHandler;
  }

  @Override
  public void execute() {
    actionHandler.apply(new RoomLightsAction(new StringId("hall"), true));
  }
}
