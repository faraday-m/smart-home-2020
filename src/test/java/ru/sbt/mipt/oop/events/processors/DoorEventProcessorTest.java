package ru.sbt.mipt.oop.events.processors;


import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.Engine;
import ru.sbt.mipt.oop.SmartHomeEngine;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.events.DoorEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.HallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.DoorEventType;
import ru.sbt.mipt.oop.events.typedefs.HallDoorEventType;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class DoorEventProcessorTest {
    private EventProcessor processor;
    private SmartHome smartHome;
    @Before
    public void initializeHome() {
        try {
            HomeLoader homeLoader = new JsonHomeLoader();
            smartHome = homeLoader.load(new FileInputStream("smart-home-1.js"));
            processor = new DoorEventProcessor();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void processDoorEvent_opensDoor() {
        ComponentId doorId = new StringId("2");
        Door testDoor = (Door) smartHome.getComponent(HomeElementType.DOOR, doorId);
        assertFalse(testDoor.isOpen());
        Event event = new DoorEvent(DoorEventType.DOOR_OPEN, doorId);
        Event newEvent = processor.processEvent(smartHome, event);
        assertTrue(testDoor.isOpen());
        assertEquals(event, newEvent);
    }

    @Test
    public void processDoorEvent_closesDoor() {
        ComponentId doorId = new StringId("3");
        Door testDoor = (Door) smartHome.getComponent(HomeElementType.DOOR, doorId);
        assertTrue(testDoor.isOpen());

        Event event = new DoorEvent(DoorEventType.DOOR_CLOSED, doorId);
        Event newEvent = processor.processEvent(smartHome, event);
        assertFalse(testDoor.isOpen());
        assertEquals(event, newEvent);
    }

    @Test
    public void processDoorEvent_closesHallDoor() {
        ComponentId doorId = new StringId("4");
        Door testDoor = (Door) smartHome.getComponent(HomeElementType.DOOR, doorId);
        assertFalse(testDoor.isOpen());

        Event event = new DoorEvent(DoorEventType.DOOR_CLOSED, doorId);
        Event newEvent = processor.processEvent(smartHome, event);
        assertFalse(testDoor.isOpen());
        assertNotSame(event, newEvent);
        assertEquals(newEvent.getType(), HallDoorEventType.LIGHTS_OFF);

    }
}
