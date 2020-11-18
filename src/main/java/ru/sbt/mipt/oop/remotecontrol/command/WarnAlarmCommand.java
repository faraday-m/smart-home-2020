package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.elements.SmartHome;

public class WarnAlarmCommand extends AbstractRemoteControlCommand {
  public WarnAlarmCommand(SmartHome smartHome) {
    super(smartHome);
  }

  @Override
  public void execute() {
    smartHome.apply(new AlarmAction(AlarmAction.AlarmState.WARN, null));
  }
}
