package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.LightsAction;

public class HomeLightCommand extends AbstractRemoteControlCommand {
  private boolean turnOn;
  
  public HomeLightCommand(boolean turnOn) {
    this.turnOn = turnOn;
  }
  
  @Override
  public void execute() {
    smartHome.apply(new LightsAction(turnOn));
  }
}
