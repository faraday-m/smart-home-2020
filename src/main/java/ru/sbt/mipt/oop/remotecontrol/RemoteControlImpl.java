package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.command.RemoteControlCommand;

import java.util.LinkedHashMap;
import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
  private String rcId;
  private Map<String, RemoteControlCommand> commands;
  
  public RemoteControlImpl(String rcId) {
    this.rcId = rcId;
    this.commands = new LinkedHashMap<>();
  }
  
  @Override
  public void onButtonPressed(String buttonCode, String rcId) {
    if (this.rcId.equals(rcId)) {
      commands.get(buttonCode).execute();
    }
  }
  
  public void setCommand(String buttonCode, RemoteControlCommand command) {
    this.commands.put(buttonCode, command);
  }
}
