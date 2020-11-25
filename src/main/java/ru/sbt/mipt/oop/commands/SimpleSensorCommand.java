package ru.sbt.mipt.oop.commands;

import ru.sbt.mipt.oop.elements.ComponentId;

public class SimpleSensorCommand implements SensorCommand {

    private final CommandType type;
    private final ComponentId objectId;

    public SimpleSensorCommand(CommandType type, ComponentId objectId) {
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
