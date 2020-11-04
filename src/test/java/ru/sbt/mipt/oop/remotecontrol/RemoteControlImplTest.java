package ru.sbt.mipt.oop.remotecontrol;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import rc.RemoteControl;
import ru.sbt.mipt.oop.configuration.RemoteControlConfiguration;
import ru.sbt.mipt.oop.elements.*;
import ru.sbt.mipt.oop.elements.alarm.AlarmState;
import ru.sbt.mipt.oop.elements.alarm.AlarmSystem;
import ru.sbt.mipt.oop.events.processors.EventDecorator;

import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={EventManagerTestConfiguration.class, RemoteControlConfiguration.class})
public class RemoteControlImplTest {
  
  @Autowired
  private EventDecorator eventDecorator;
  @Autowired
  private RemoteControl rc;
  @Autowired
  private ApplicationContext context;
  
  private AlarmSystem alarm;
  
  @Before
  public void initialize() {
    alarm = context.getBean(AlarmSystem.class);
    alarm.deactivate("activationCode");
  }
  
  @Test
  public void AlarmActivatedTest() {
      assertTrue(alarm.getAlarmState().equals(AlarmState.DEACTIVATED));
      rc.onButtonPressed("A", "rc");
      assertTrue(alarm.getAlarmState().equals(AlarmState.ACTIVATED));
  }
  
  
  @Test
  public void AlarmWarningTest() {
    AlarmSystem alarm = context.getBean(AlarmSystem.class);
    assertTrue(alarm.getAlarmState().equals(AlarmState.DEACTIVATED));
    alarm.activate("activationCode");
    assertTrue(alarm.getAlarmState().equals(AlarmState.ACTIVATED));
    rc.onButtonPressed("B", "rc");
    assertTrue(alarm.getAlarmState().equals(AlarmState.WARNING));
  }
  
  
  @Test
  public void HallDoorTest() {
    Door door = ((Map<ComponentId, Door>)context.getBean("hallDoors")).values().stream().findFirst().get();
    rc.onButtonPressed("C", "rc");
    assertFalse(door.isOpen());
  }
  
  
  @Test
  public void HallLightsTest() {
    Map<ComponentId, Light> hallLights = (Map<ComponentId, Light>)context.getBean("hallLights");
    rc.onButtonPressed("D", "rc");
    assertTrue(hallLights.values().stream().allMatch(Light::isOn));
  }
  
  @Test
  public void HomeLightsTest() {
    Map<ComponentId, Light> hallLights = (Map<ComponentId, Light>)context.getBean("hallLights");
    Map<ComponentId, Light> kitchenLights = (Map<ComponentId, Light>)context.getBean("kitchenLights");
    rc.onButtonPressed("0", "rc");
    assertFalse(hallLights.values().stream().allMatch(Light::isOn));
    assertFalse(kitchenLights.values().stream().allMatch(Light::isOn));
    rc.onButtonPressed("1", "rc");
    assertTrue(hallLights.values().stream().allMatch(Light::isOn));
    assertTrue(kitchenLights.values().stream().allMatch(Light::isOn));
  }
}
