package ru.sbt.mipt.oop.events.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.events.DoorEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.EventType;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class HallDoorEventProcessorTest {
    private EventProcessor processor;
    private SmartHome smartHome;
    private Map<ComponentId, Light> kitchenTestLights;
    private Map<ComponentId, Light> hallTestLights;
    private Map<ComponentId, Door> testDoors;

    @Before
    public void initializeHome() {
        kitchenTestLights = new LinkedHashMap<>();
        kitchenTestLights.put(new StringId("1"), new Light("1", false));
        kitchenTestLights.put(new StringId("2"), new Light("2", true));
        hallTestLights = new LinkedHashMap<>();
        hallTestLights.put(new StringId("3"), new Light("3", true));
        testDoors = new LinkedHashMap<>();
        testDoors.put(new StringId("1"), new Door("1", true));
        Room kitchen = new Room(kitchenTestLights, new LinkedHashMap<>(), "kitchen");
        Room hall = new Room(hallTestLights, testDoors, "hall");
        smartHome = new SmartHome(Arrays.asList(kitchen, hall));
        processor = new HallDoorEventProcessor();
    }

    @Test
    public void processHallDoorEvent_lightsOff() {
        assertTrue(kitchenTestLights.get(new StringId("2")).isOn());
        assertTrue(hallTestLights.get(new StringId("3")).isOn());
        Event event = new DoorEvent(EventType.DOOR_CLOSED, new StringId("1"));
        processor.processEvent(smartHome, event);
        assertFalse(kitchenTestLights.get(new StringId("2")).isOn());
        assertFalse(hallTestLights.get(new StringId("3")).isOn());
    }
}
