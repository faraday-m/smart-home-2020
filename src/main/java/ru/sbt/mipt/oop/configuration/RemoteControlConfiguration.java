package ru.sbt.mipt.oop.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.remotecontrol.RemoteControlBuilder;
import ru.sbt.mipt.oop.remotecontrol.command.*;

@Configuration
public class RemoteControlConfiguration {
  @Bean
  public RemoteControlRegistry getRemoteControlRegistry() {
    return new RemoteControlRegistry();
  }
  
  @Bean
  public RemoteControlCommand getActivateAlarm() {
    return new ActivateAlarmCommand("activationCode");
  }
  
  @Bean
  public RemoteControlCommand getCloseHallDoor() {
    return new CloseHallDoorCommand();
  }
  
  @Bean
  public RemoteControlCommand getHallLightsOn() {
    return new HallLightsOnCommand();
  }
  
  @Bean
  public RemoteControlCommand getHomeLightsOn() {
    return new HomeLightCommand(true);
  }
  
  @Bean
  public RemoteControlCommand getHomeLightsOff() {
    return new HomeLightCommand(false);
  }
  
  @Bean
  public RemoteControlCommand getWarnAlarm() {
    return new WarnAlarmCommand();
  }
  
  @Bean
  public RemoteControl getRemoteControl() {
    RemoteControl rc = new RemoteControlBuilder("rc")
        .setCommand("A", getActivateAlarm())
        .setCommand("B", getWarnAlarm())
        .setCommand("C", getCloseHallDoor())
        .setCommand("D", getHallLightsOn())
        .setCommand("0", getHomeLightsOff())
        .setCommand("1", getHomeLightsOn())
        .build();
    getRemoteControlRegistry().registerRemoteControl(rc, "rc");
    return rc;
  }
}
