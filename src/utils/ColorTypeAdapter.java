package utils;

import com.google.gson.*;

import java.awt.*;
import java.lang.reflect.Type;

public class ColorTypeAdapter implements JsonSerializer<Color>, JsonDeserializer<Color> {

    @Override
    public JsonElement serialize(Color src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject json = new JsonObject();
        json.addProperty("r", src.getRed());
        json.addProperty("g", src.getGreen());
        json.addProperty("b", src.getBlue());
        json.addProperty("a", src.getAlpha()); // Include alpha channel if needed
        return json;
    }

    @Override
    public Color deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        int r = jsonObject.get("r").getAsInt();
        int g = jsonObject.get("g").getAsInt();
        int b = jsonObject.get("b").getAsInt();
        int a = jsonObject.has("a") ? jsonObject.get("a").getAsInt() : 255; // Default alpha
        return new Color(r, g, b, a);
    }
}
