package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;
import ru.sbt.mipt.oop.remotecontrol.command.RemoteControlCommand;

import java.util.LinkedHashMap;
import java.util.Map;

public class RemoteControlImpl implements RemoteControl {
  private final String rcId;
  private final Map<String, RemoteControlCommand> commands;
  
  public RemoteControlImpl(String rcId, Map<String, RemoteControlCommand> commands) {
    this.rcId = rcId;
    this.commands = commands;
  }
  
  @Override
  public void onButtonPressed(String buttonCode, String rcId) {
    if (this.rcId.equals(rcId)) {
      RemoteControlCommand command = commands.get(buttonCode);
      if (command != null) {
        command.execute();
      }
    }
  }
}
