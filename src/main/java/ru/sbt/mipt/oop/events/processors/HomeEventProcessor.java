package ru.sbt.mipt.oop.events.processors;

import ru.sbt.mipt.oop.elements.Light;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.EventType;

public class HomeEventProcessor implements EventProcessor {
  @Override
  public void processEvent(SmartHome smartHome, Event event) {
    if (event.getType().isHomeEvent()) {
      smartHome.apply(event, c -> ((Light) c).setOn(event.getType().equals(EventType.HOME_LIGHTS_ON)));
    }
  }
}
