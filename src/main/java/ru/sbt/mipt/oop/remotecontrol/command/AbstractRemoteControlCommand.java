package ru.sbt.mipt.oop.remotecontrol.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.elements.SmartHome;

@Component
public abstract class AbstractRemoteControlCommand implements RemoteControlCommand {
  @Autowired
  protected SmartHome smartHome;
  
  @Override
  public abstract void execute();
}
