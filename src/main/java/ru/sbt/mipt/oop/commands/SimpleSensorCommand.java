package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.elements.DeviceId;

public class SimpleSensorCommand implements SensorCommand {

    private final CommandType type;
    private final DeviceId objectId;

    public SimpleSensorCommand(CommandType type, DeviceId objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    @Override
    public String toString() {
        return "SensorCommand{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public void sendCommand() {
        System.out.println("Pretent we're sending command " + this);
    }
}
