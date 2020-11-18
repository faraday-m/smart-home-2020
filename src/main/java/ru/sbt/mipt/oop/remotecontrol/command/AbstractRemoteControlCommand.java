package ru.sbt.mipt.oop.remotecontrol.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.elements.SmartHome;

public abstract class AbstractRemoteControlCommand implements RemoteControlCommand {
  public AbstractRemoteControlCommand(ActionHandler actionHandler) {
    this.actionHandler = actionHandler;
  }

  protected ActionHandler actionHandler;
  
  @Override
  public abstract void execute();
}
