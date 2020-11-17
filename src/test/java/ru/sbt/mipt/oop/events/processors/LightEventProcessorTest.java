package ru.sbt.mipt.oop.events.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.LightEvent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static ru.sbt.mipt.oop.events.typedefs.EventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.events.typedefs.EventType.LIGHT_ON;

public class LightEventProcessorTest {
    private EventProcessor processor;
    private SmartHome smartHome;
    private Map<ComponentId, Light> testLights;

    @Before
    public void initializeHome() {
        testLights = new LinkedHashMap<>();
        testLights.put(new StringId("1"), new Light("1", false));
        testLights.put(new StringId("2"), new Light("2", true));
        Room room1 = new Room(testLights, new LinkedHashMap<>(), "kitchen");
        smartHome = new SmartHome(Collections.singletonList(room1));
        processor = new LightEventProcessor(smartHome);
    }

    @Test
    public void processLightEvent_turnsOn() {
        ComponentId lightId = new StringId("1");
        assertFalse(testLights.get(lightId).isOn());
        Event event = new LightEvent(LIGHT_ON, lightId);
        processor.processEvent(event);
        assertTrue(testLights.get(lightId).isOn());
    }

    @Test
    public void processLightEvent_turnsOff() {
        ComponentId lightId = new StringId("2");
        assertTrue(testLights.get(lightId).isOn());
        Event event = new LightEvent(LIGHT_OFF, lightId);
        processor.processEvent(event);
        assertFalse(testLights.get(lightId).isOn());
    }
}
