package ru.sbt.mipt.oop.events.processors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.commands.SMSNotifier;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import static org.junit.Assert.assertTrue;

public class AlarmWarningTest {
    public static final String ACTIVATION_CODE_1 = "code";
    public static final String ACTIVATION_CODE_2 = "other";

    private AlarmSystem alarmSystem;

    @Before
    public void initializeHome() {
        alarmSystem = new AlarmSystem(new SMSNotifier());
    }

    @Test
    public void alarmStateMachineTest_activate() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        assertTrue(alarmSystem.isActivated());
    }


    @Test
    public void alarmStateMachineTest_deactivateWithRightKey() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        assertTrue(alarmSystem.isActivated());
        alarmSystem.deactivate(ACTIVATION_CODE_1);
        assertTrue(alarmSystem.isDeactivated());
    }


    @Test
    public void alarmStateMachineTest_deactivateWithInvalidKey() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        alarmSystem.deactivate(ACTIVATION_CODE_2);
        assertTrue(alarmSystem.isWarned());
    }

    @Test
    public void alarmStateMachineTest_stopWarningWithRightKey() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        alarmSystem.deactivate(ACTIVATION_CODE_2);
        assertTrue(alarmSystem.isWarned());
        alarmSystem.deactivate(ACTIVATION_CODE_1);
        assertTrue(alarmSystem.isDeactivated());
    }


    @Test
    public void alarmStateMachineTest_stopWarningWithInvalidKey() {
        alarmSystem.activate(ACTIVATION_CODE_1);
        alarmSystem.deactivate(ACTIVATION_CODE_2);
        assertTrue(alarmSystem.isWarned());
        alarmSystem.deactivate(ACTIVATION_CODE_2);
        assertTrue(alarmSystem.isWarned());
    }


    @Test
    public void alarmStateMachineTest_turnWarningOn() {
        alarmSystem.warn();
        assertTrue(alarmSystem.isWarned());
    }
}
