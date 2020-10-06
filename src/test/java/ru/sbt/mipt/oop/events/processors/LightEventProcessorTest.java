package ru.sbt.mipt.oop.events.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.Engine;
import ru.sbt.mipt.oop.SmartHomeEngine;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.events.DoorEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.LightEvent;
import ru.sbt.mipt.oop.events.typedefs.DoorEventType;
import ru.sbt.mipt.oop.events.typedefs.LightEventType;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class LightEventProcessorTest {
    private EventProcessor processor;
    private SmartHome smartHome;
    @Before
    public void initializeHome() {
        try {
            HomeLoader homeLoader = new JsonHomeLoader();
            smartHome = homeLoader.load(new FileInputStream("smart-home-1.js"));
            processor = new LightEventProcessor();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void processLightEvent_turnsOn() {
        ComponentId lightId = new StringId("4");
        Light testLight = (Light) smartHome.getComponent(HomeElementType.LIGHT, lightId);
        assertFalse(testLight.isOn());
        Event event = new LightEvent(LightEventType.LIGHT_ON, lightId);
        Event newEvent = processor.processEvent(smartHome, event);
        assertTrue(testLight.isOn());
        assertEquals(event, newEvent);
    }

    @Test
    public void processLightEvent_turnsOff() {
        ComponentId lightId = new StringId("2");
        Light testLight = (Light) smartHome.getComponent(HomeElementType.LIGHT, lightId);
        assertTrue(testLight.isOn());
        Event event = new LightEvent(LightEventType.LIGHT_OFF, lightId);
        Event newEvent = processor.processEvent(smartHome, event);
        assertFalse(testLight.isOn());
        assertEquals(event, newEvent);
    }
}
