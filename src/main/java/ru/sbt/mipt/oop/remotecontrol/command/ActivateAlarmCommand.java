package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.elements.SmartHome;

public class ActivateAlarmCommand extends AbstractRemoteControlCommand {
  private String activationCode;
  
  public ActivateAlarmCommand(SmartHome smartHome, String activationCode) {
    super(smartHome);
    this.activationCode = activationCode;
  }
  
  @Override
  public void execute() {
    smartHome.apply(new AlarmAction(AlarmAction.AlarmState.ACTIVATE, activationCode));
  }
}
