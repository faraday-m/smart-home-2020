package ru.sbt.mipt.oop.utils;

import com.google.gson.*;
import ru.sbt.mipt.oop.elements.StringId;

import java.lang.reflect.Type;

public class StringIdInterfaceAdapter implements JsonSerializer, JsonDeserializer {
    @Override
    public Object deserialize(JsonElement jsonElement, Type type,
                         JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new StringId(jsonElement.getAsJsonPrimitive().getAsString());
    }

    @Override
    public JsonElement serialize(Object jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(jsonElement.toString());
    }

}
