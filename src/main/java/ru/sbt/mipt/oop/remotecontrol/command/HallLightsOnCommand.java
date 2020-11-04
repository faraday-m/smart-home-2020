package ru.sbt.mipt.oop.remotecontrol.command;

import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.events.HomeEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class HallLightsOnCommand extends AbstractRemoteControlCommand {
  @Override
  public void execute() {
    eventDecorator.handleEvent(new HomeEvent(EventType.ROOM_LIGHTS_ON, new StringId("hall")));
  }
}
