package ru.sbt.mipt.oop.init;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import ru.sbt.mipt.oop.elements.SmartHome;
import ru.sbt.mipt.oop.elements.ComponentId;
import ru.sbt.mipt.oop.utils.StringIdInterfaceAdapter;

import java.io.*;

public class JsonHomeLoader implements HomeLoader {
    private final Gson gson = new GsonBuilder().registerTypeAdapter(ComponentId.class, new StringIdInterfaceAdapter()).setPrettyPrinting().create();

    public SmartHome load(InputStream stream)  {
        JsonReader jsonReader = new JsonReader(new InputStreamReader(stream));
        return gson.fromJson(jsonReader, SmartHome.class);
    }

    public void save(SmartHome smartHome, OutputStream stream) {
        String jsonString = gson.toJson(smartHome);
        try (OutputStreamWriter osw = new OutputStreamWriter(stream)) {
            osw.write(jsonString);
        } catch (IOException e) {
            System.out.println("Error while writing to JSON");
        }
    }
}
