package ru.sbt.mipt.oop.commands;


public class SMSNotifier implements Notifier {
    public void sendNotification(String parameters) {
        System.out.printf("Sending sms: %s\n", parameters);
    }
}
