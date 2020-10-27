package ru.sbt.mipt.oop.events.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static ru.sbt.mipt.oop.Application.ACTIVATION_CODE_1;
import static ru.sbt.mipt.oop.Application.ACTIVATION_CODE_2;

public class AlarmWarningTest {
    private EventProcessor processor;
    private SmartHome smartHome;
    private Map<ComponentId, Door> testDoors;
    private AlarmSystem alarmSystem;

    @Before
    public void initializeHome() {
        Door door = new Door(new StringId("1"), false);
        testDoors = new LinkedHashMap<>();
        testDoors.put(new StringId("1"), door);
        Room room = new Room(new LinkedHashMap<>(), testDoors, "kitchen");
        alarmSystem = new AlarmSystem();
        smartHome = new SmartHome(Collections.singletonList(room), alarmSystem);
        processor = new AlarmProcessor();
    }

    @Test
    public void alarmStateMachineTest_activate() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        assertEquals(AlarmState.ACTIVATED, alarmSystem.getAlarmState());
    }


    @Test
    public void alarmStateMachineTest_deactivateWithRightKey() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        assertEquals(AlarmState.ACTIVATED, alarmSystem.getAlarmState());
        alarmSystem.deactivate(ACTIVATION_CODE_1);
        assertEquals(AlarmState.DEACTIVATED, alarmSystem.getAlarmState());
    }


    @Test
    public void alarmStateMachineTest_deactivateWithInvalidKey() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        alarmSystem.deactivate(ACTIVATION_CODE_2);
        assertEquals(AlarmState.WARNING, alarmSystem.getAlarmState());
    }

    @Test
    public void alarmStateMachineTest_stopWarningWithRightKey() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        alarmSystem.deactivate(ACTIVATION_CODE_2);
        assertEquals(AlarmState.WARNING, alarmSystem.getAlarmState());
        alarmSystem.deactivate(ACTIVATION_CODE_1);
        assertEquals(AlarmState.DEACTIVATED, alarmSystem.getAlarmState());
    }


    @Test
    public void alarmStateMachineTest_stopWarningWithInvalidKey() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        alarmSystem.deactivate(ACTIVATION_CODE_2);
        assertEquals(AlarmState.WARNING, alarmSystem.getAlarmState());
        alarmSystem.deactivate(ACTIVATION_CODE_2);
        assertEquals(AlarmState.WARNING, alarmSystem.getAlarmState());
    }


    @Test
    public void alarmStateMachineTest_turnWarningOn() {
        alarmSystem.warn();
        assertEquals(AlarmState.WARNING, alarmSystem.getAlarmState());
    }
}
