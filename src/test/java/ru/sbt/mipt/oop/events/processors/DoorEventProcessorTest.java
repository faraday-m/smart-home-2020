package ru.sbt.mipt.oop.events.processors;


import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.actions.ActionDecorator;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.commands.SMSNotifier;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.DoorEvent;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.typedefs.EventType;
import java.util.*;

import static org.junit.Assert.*;

public class DoorEventProcessorTest {
    private EventProcessor processor;
    private SmartHome smartHome;
    private Map<ComponentId, Door> testDoors;

    @Before
    public void initializeHome() {
        testDoors = new LinkedHashMap<>();
        testDoors.put(new StringId("1"), new Door("1", false));
        testDoors.put(new StringId("2"), new Door("2", true));
        Room room1 = new Room(new LinkedHashMap<>(), testDoors, "kitchen");
        AlarmSystem alarmSystem = new AlarmSystem(new SMSNotifier());
        smartHome = new SmartHome(Collections.singletonList(room1), alarmSystem);
        ActionHandler actionHandler = new ActionDecorator(smartHome, alarmSystem);
        processor = new DoorEventProcessor(actionHandler);
    }

    @Test
    public void processDoorEvent_opensDoor() {
        ComponentId doorId = new StringId("1");
        assertFalse(testDoors.get(doorId).isOpen());
        Event event = new DoorEvent(EventType.DOOR_OPEN, doorId);
        processor.processEvent(event);
        assertTrue(testDoors.get(doorId).isOpen());
    }

    @Test
    public void processDoorEvent_closesDoor() {
        ComponentId doorId = new StringId("2");
        assertTrue(testDoors.get(doorId).isOpen());
        Event event = new DoorEvent(EventType.DOOR_CLOSED, doorId);
        processor.processEvent(event);
        assertFalse(testDoors.get(doorId).isOpen());
    }
}
