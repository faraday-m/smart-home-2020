package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.AlarmAction;

public class WarnAlarmCommand extends AbstractRemoteControlCommand {
  @Override
  public void execute() {
    smartHome.apply(new AlarmAction(AlarmAction.AlarmState.WARN, null));
  }
}
