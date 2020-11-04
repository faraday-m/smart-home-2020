package ru.sbt.mipt.oop.remotecontrol.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.events.processors.EventDecorator;

@Component
public abstract class AbstractRemoteControlCommand implements RemoteControlCommand {
  @Autowired
  protected EventDecorator eventDecorator;
  
  @Override
  public abstract void execute();
}
