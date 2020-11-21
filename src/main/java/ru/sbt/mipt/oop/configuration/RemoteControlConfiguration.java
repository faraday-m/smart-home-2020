package ru.sbt.mipt.oop.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlImpl;
import ru.sbt.mipt.oop.remotecontrol.command.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Import({EventManagerConfiguration.class})
public class RemoteControlConfiguration {
  @Autowired
  private ActionHandler actionHandler;

  @Bean
  public RemoteControlRegistry remoteControlRegistry() {
    return new RemoteControlRegistry();
  }

  @Bean
  public RemoteControlCommand activateAlarm() {
    return new ActivateAlarmCommand(actionHandler, "activationCode");
  }

  @Bean
  public RemoteControlCommand closeHallDoor() {
    return new CloseHallDoorCommand(actionHandler);
  }

  @Bean
  public RemoteControlCommand hallLightsOn() {
    return new HallLightsOnCommand(actionHandler);
  }

  @Bean
  public RemoteControlCommand homeLightsOn() {
    return new HomeLightCommand(actionHandler,true);
  }

  @Bean
  public RemoteControlCommand homeLightsOff() {
    return new HomeLightCommand(actionHandler,false);
  }

  @Bean
  public RemoteControlCommand warnAlarm() {
    return new WarnAlarmCommand(actionHandler);
  }

  @Bean
  public Map<String, RemoteControlCommand> commands() {
      Map<String, RemoteControlCommand> commands = new LinkedHashMap<>();
      commands.put("A", activateAlarm());
      commands.put("B", warnAlarm());
      commands.put("C", closeHallDoor());
      commands.put("D", hallLightsOn());
      commands.put("0", homeLightsOff());
      commands.put("1", homeLightsOn());
      return commands;
  }

  @Bean
  public RemoteControl remoteControl() {
     RemoteControl rc = new RemoteControlImpl("rc", commands());
     remoteControlRegistry().registerRemoteControl(rc, "rc");
     return rc;
  }
}
