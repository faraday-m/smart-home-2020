package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class WarnAlarmCommand extends AbstractRemoteControlCommand {
  @Override
  public void execute() {
    eventDecorator.handleEvent(new AlarmEvent(EventType.ALARM_WARNING, new StringId("ALARM"), null));
  }
}
