package ru.sbt.mipt.oop.events.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.elements.HomeElementType;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.StringId;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.init.HomeLoader;
import ru.sbt.mipt.oop.init.JsonHomeLoader;

import java.io.FileInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static ru.sbt.mipt.oop.Application.ACTIVATION_CODE_1;
import static ru.sbt.mipt.oop.Application.ACTIVATION_CODE_2;

public class AlarmWarningTest {
    private EventProcessor processor;
    private SmartHome smartHome;

    @Before
    public void initializeHome() {
        try {
            HomeLoader homeLoader = new JsonHomeLoader();
            smartHome = homeLoader.load(new FileInputStream("smart-home-1.js"));
            processor = new AlarmProcessor();
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void alarmStateMachineTest_activate() {
        AlarmSystem alarm = new AlarmSystem(new StringId("ALARM"));
        smartHome.addHomeComponent(HomeElementType.ALARM, alarm);

        alarm.activate(ACTIVATION_CODE_1);
        assertEquals(AlarmState.ACTIVATED, alarm.getAlarmState());
    }


    @Test
    public void alarmStateMachineTest_deactivateWithRightKey() {
        AlarmSystem alarm = new AlarmSystem(new StringId("ALARM"));
        smartHome.addHomeComponent(HomeElementType.ALARM, alarm);
        alarm.activate(ACTIVATION_CODE_1);
        alarm.deactivate(ACTIVATION_CODE_1);
        assertEquals(AlarmState.DEACTIVATED, alarm.getAlarmState());
    }


    @Test
    public void alarmStateMachineTest_deactivateWithInvalidKey() {
        AlarmSystem alarm = new AlarmSystem(new StringId("ALARM"));
        smartHome.addHomeComponent(HomeElementType.ALARM, alarm);
        alarm.activate(ACTIVATION_CODE_1);
        alarm.deactivate(ACTIVATION_CODE_2);
        assertEquals(AlarmState.WARNING, alarm.getAlarmState());
    }

    @Test
    public void alarmStateMachineTest_stopWarningWithRightKey() {
        AlarmSystem alarm = new AlarmSystem(new StringId("ALARM"));
        smartHome.addHomeComponent(HomeElementType.ALARM, alarm);
        alarm.activate(ACTIVATION_CODE_1);
        alarm.deactivate(ACTIVATION_CODE_2);
        assertEquals(AlarmState.WARNING, alarm.getAlarmState());
        alarm.deactivate(ACTIVATION_CODE_1);
        assertEquals(AlarmState.DEACTIVATED, alarm.getAlarmState());
    }


    @Test
    public void alarmStateMachineTest_stopWarningWithInvalidKey() {
        AlarmSystem alarm = new AlarmSystem(new StringId("ALARM"));
        smartHome.addHomeComponent(HomeElementType.ALARM, alarm);
        alarm.activate(ACTIVATION_CODE_1);
        alarm.deactivate(ACTIVATION_CODE_2);
        assertEquals(AlarmState.WARNING, alarm.getAlarmState());
        alarm.deactivate(ACTIVATION_CODE_2);
        assertEquals(AlarmState.WARNING, alarm.getAlarmState());
    }


    @Test
    public void alarmStateMachineTest_turnWarningOn() {
        AlarmSystem alarm = new AlarmSystem(new StringId("ALARM"));
        smartHome.addHomeComponent(HomeElementType.ALARM, alarm);
        alarm.warn();
        assertEquals(AlarmState.WARNING, alarm.getAlarmState());
    }
}
