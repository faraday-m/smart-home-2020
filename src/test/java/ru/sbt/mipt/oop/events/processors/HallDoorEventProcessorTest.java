package ru.sbt.mipt.oop.events.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.commands.CommandType;
import ru.sbt.mipt.oop.commands.SimpleSensorCommand;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.events.Event;
import ru.sbt.mipt.oop.events.HallDoorEvent;
import ru.sbt.mipt.oop.events.typedefs.EventType;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.function.Predicate;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class HallDoorEventProcessorTest {
    private EventProcessor processor;
    private SmartHome smartHome;
    @Before
    public void initializeHome() {
        try {
            HomeLoader homeLoader = new JsonHomeLoader();
            smartHome = homeLoader.load(new FileInputStream("smart-home-1.js"));
            processor = new HallDoorEventProcessor();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void processHallDoorEvent_lightsOff() {
        Event event = new HallDoorEvent(EventType.LIGHTS_OFF, smartHome.getId(), new SimpleSensorCommand(CommandType.LIGHT_OFF, smartHome.getId()));
        Event newEvent = processor.processEvent(smartHome, event);
        assertEquals(event, newEvent);
        Collection<Light> lights = (Collection<Light>) smartHome.getComponents((HomeComponent c) -> c.getType().equals(HomeElementType.LIGHT));
        assertTrue(lights.stream().map(Light::isOn).allMatch(Predicate.isEqual(false)));
    }
}
