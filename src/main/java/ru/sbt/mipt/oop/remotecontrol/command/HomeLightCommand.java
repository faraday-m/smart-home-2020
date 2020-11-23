package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.LightsAction;
import ru.sbt.mipt.oop.elements.SmartHome;

public class HomeLightCommand implements RemoteControlCommand {
  private boolean turnOn;
  private ActionHandler actionHandler;
  
  public HomeLightCommand(ActionHandler actionHandler, boolean turnOn) {
    this.actionHandler = actionHandler;
    this.turnOn = turnOn;
  }
  
  @Override
  public void execute() {
    actionHandler.apply(new LightsAction(turnOn));
  }
}
