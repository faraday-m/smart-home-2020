package ru.sbt.mipt.oop.remotecontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import rc.RemoteControl;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.actions.ActionHandler;
import ru.sbt.mipt.oop.configuration.EventManagerConfiguration;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.command.*;

@Configuration
@Import({EventManagerTestConfiguration.class})
public class RemoteControlTestConfiguration {
  @Autowired
  private ActionHandler actionHandler;

  @Bean
  public RemoteControlRegistry getRemoteControlRegistry() {
    return new RemoteControlRegistry();
  }

  @Bean
  public RemoteControlCommand getActivateAlarm() {
    return new ActivateAlarmCommand(actionHandler, "activationCode");
  }

  @Bean
  public RemoteControlCommand getCloseHallDoor() {
    return new CloseHallDoorCommand(actionHandler);
  }

  @Bean
  public RemoteControlCommand getHallLightsOn() {
    return new HallLightsOnCommand(actionHandler);
  }

  @Bean
  public RemoteControlCommand getHomeLightsOn() {
    return new HomeLightCommand(actionHandler,true);
  }

  @Bean
  public RemoteControlCommand getHomeLightsOff() {
    return new HomeLightCommand(actionHandler,false);
  }

  @Bean
  public RemoteControlCommand getWarnAlarm() {
    return new WarnAlarmCommand(actionHandler);
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
