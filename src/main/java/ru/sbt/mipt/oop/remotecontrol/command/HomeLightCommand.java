package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.actions.LightsAction;
import ru.sbt.mipt.oop.elements.SmartHome;

public class HomeLightCommand extends AbstractRemoteControlCommand {
  private boolean turnOn;
  
  public HomeLightCommand(ActionHandler actionHandler, boolean turnOn) {
    super(actionHandler);
    this.turnOn = turnOn;
  }
  
  @Override
  public void execute() {
    actionHandler.apply(new LightsAction(turnOn));
  }
}
