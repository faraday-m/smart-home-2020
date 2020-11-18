package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.AlarmAction;

public class ActivateAlarmCommand extends AbstractRemoteControlCommand {
  private String activationCode;
  
  public ActivateAlarmCommand(String activationCode) {
    this.activationCode = activationCode;
  }
  
  @Override
  public void execute() {
    smartHome.apply(new AlarmAction(AlarmAction.AlarmState.ACTIVATE, activationCode));
  }
}
