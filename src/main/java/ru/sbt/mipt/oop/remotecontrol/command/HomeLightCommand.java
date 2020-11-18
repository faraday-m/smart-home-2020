package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.actions.LightsAction;
import ru.sbt.mipt.oop.elements.SmartHome;

public class HomeLightCommand extends AbstractRemoteControlCommand {
  private boolean turnOn;
  
  public HomeLightCommand(SmartHome smartHome, boolean turnOn) {
    super(smartHome);
    this.turnOn = turnOn;
  }
  
  @Override
  public void execute() {
    smartHome.apply(new LightsAction(turnOn));
  }
}
