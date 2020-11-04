package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.command.RemoteControlCommand;

public class RemoteControlBuilder {
  private RemoteControlImpl result;
  
  public RemoteControlBuilder(String rcId) {
    result = new RemoteControlImpl(rcId);
  }
  
  public RemoteControlBuilder setCommand(String buttonCode, RemoteControlCommand command) {
    result.setCommand(buttonCode, command);
    return this;
  }
  
  public RemoteControl build() {
    return result;
  }
}
