package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.elements.SmartHome;

public class WarnAlarmCommand implements RemoteControlCommand {
  private ActionHandler actionHandler;

  public WarnAlarmCommand(ActionHandler actionHandler) {
    this.actionHandler = actionHandler;
  }

  @Override
  public void execute() {
    actionHandler.apply(new AlarmAction(AlarmAction.AlarmState.WARN, null));
  }
}
