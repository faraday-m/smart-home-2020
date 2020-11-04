package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.HomeEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class HomeLightCommand extends AbstractRemoteControlCommand {
  private boolean turnOn;
  
  public HomeLightCommand(boolean turnOn) {
    this.turnOn = turnOn;
  }
  
  @Override
  public void execute() {
    eventDecorator.handleEvent(new HomeEvent((turnOn ? EventType.HOME_LIGHTS_ON : EventType.HOME_LIGHTS_OFF), SmartHome.id));
  }
}
