package ru.sbt.mipt.oop.remotecontrol.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.elements.SmartHome;

public abstract class AbstractRemoteControlCommand implements RemoteControlCommand {
  public AbstractRemoteControlCommand(SmartHome smartHome) {
    this.smartHome = smartHome;
  }

  protected SmartHome smartHome;
  
  @Override
  public abstract void execute();
}
