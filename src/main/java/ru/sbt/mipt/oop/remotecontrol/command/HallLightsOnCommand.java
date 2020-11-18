package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.RoomLightsAction;
import ru.sbt.mipt.oop.elements.StringId;

public class HallLightsOnCommand extends AbstractRemoteControlCommand {
  @Override
  public void execute() {
    smartHome.apply(new RoomLightsAction(new StringId("hall"), true));
  }
}
