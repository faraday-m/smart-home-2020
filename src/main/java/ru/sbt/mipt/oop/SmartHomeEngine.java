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
        fillProcessorComposite(processorComposite);
    }

    private static void fillProcessorComposite(EventProcessorComposite processorComposite) {
        processorComposite.addEventProcessor(new DoorEventProcessor());
        processorComposite.addEventProcessor(new LightEventProcessor());
    }


    public void start() {
        // начинаем цикл обработки событий
        Event event = null;
        do {
            event = processorComposite.processEvent(smartHome, event);
        } while (event != null);
    }
}
