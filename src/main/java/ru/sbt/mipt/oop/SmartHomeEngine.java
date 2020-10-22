package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.events.processors.*;

public class SmartHomeEngine implements Engine {
    EventProcessorComposite processorComposite;
    SmartHome smartHome;

    public SmartHomeEngine(SmartHome home, EventProcessorComposite processorComposite) {
        this.smartHome = home;
        this.processorComposite = processorComposite;
        processorComposite.addEventProcessor(EventProcessorType.DOOR, new DoorEventProcessor());
        processorComposite.addEventProcessor(EventProcessorType.LIGHT, new LightEventProcessor());
        processorComposite.addEventProcessor(EventProcessorType.HALL_DOOR, new HallDoorEventProcessor());
    }

    public void start() {
        // начинаем цикл обработки событий
        Event event = null;
        do {
            event = processorComposite.processEvent(smartHome, event);
        } while (event != null);
    }
}
