package ru.sbt.mipt.oop.init;

import ru.sbt.mipt.oop.elements.SmartHome;

import java.io.InputStream;
import java.io.OutputStream;

public interface HomeLoader {
    SmartHome load(InputStream stream);
    void save(SmartHome smartHome, OutputStream stream);
}
