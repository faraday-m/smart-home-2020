package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.elements.SmartHome;

public class ActivateAlarmCommand implements RemoteControlCommand {
  private String activationCode;
  private ActionHandler actionHandler;
  
  public ActivateAlarmCommand(ActionHandler actionHandler, String activationCode) {
    this.actionHandler = actionHandler;
    this.activationCode = activationCode;
  }
  
  @Override
  public void execute() {
    actionHandler.apply(new AlarmAction(AlarmAction.AlarmState.ACTIVATE, activationCode));
  }
}
