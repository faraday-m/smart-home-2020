package ru.sbt.mipt.oop.remotecontrol.command;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.AlarmEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class ActivateAlarmCommand extends AbstractRemoteControlCommand {
  private String activationCode;
  
  public ActivateAlarmCommand(String activationCode) {
    this.activationCode = activationCode;
  }
  
  @Override
  public void execute() {
    eventDecorator.handleEvent(new AlarmEvent(EventType.ALARM_ACTIVATE, new StringId("ALARM"), activationCode));
  }
}
