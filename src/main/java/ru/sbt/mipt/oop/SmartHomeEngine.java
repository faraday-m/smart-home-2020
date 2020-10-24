package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.*;

public class SmartHomeEngine implements Engine {
    EventProcessorComposite processorComposite;
    SmartHome smartHome;

    private static void fillProcessorComposite(EventProcessorComposite processorComposite) {
        processorComposite.addEventProcessor(new DoorEventProcessor());
        processorComposite.addEventProcessor(new LightEventProcessor());
    }

    public SmartHomeEngine(SmartHome home, EventProcessorComposite processorComposite) {
        smartHome = home;
        this.processorComposite = processorComposite;
        fillProcessorComposite(processorComposite);
    }

    public void start() {
        // начинаем цикл обработки событий
        SensorEvent event = null;
        do {
            event = processorComposite.processEvent(smartHome, event);
        } while (event != null);
    }
}
