package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.AlarmAction;
import ru.sbt.mipt.oop.elements.SmartHome;

public class WarnAlarmCommand extends AbstractRemoteControlCommand {
  public WarnAlarmCommand(ActionHandler actionHandler) {
    super(actionHandler);
  }

  @Override
  public void execute() {
    actionHandler.apply(new AlarmAction(AlarmAction.AlarmState.WARN, null));
  }
}
