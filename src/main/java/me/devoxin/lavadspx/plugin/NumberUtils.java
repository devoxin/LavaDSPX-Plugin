package me.devoxin.lavadspx.plugin;

import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import org.jetbrains.annotations.Nullable;

public class NumberUtils {
    @Nullable
    public static Integer parseIntElement(JsonElement data, String key) {
        if (!(data instanceof JsonObject obj)) {
            return null;
        }

        JsonElement element = obj.get(key);

        if (!(element instanceof JsonPrimitive elementValue)) {
            return null;
        }

        try {
            return Integer.parseInt(elementValue.getContent());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Nullable
    public static Float parseFloatElement(JsonElement data, String key) {
        if (!(data instanceof JsonObject obj)) {
            return null;
        }

        JsonElement element = obj.get(key);

        if (!(element instanceof JsonPrimitive elementValue)) {
            return null;
        }

        try {
            return Float.parseFloat(elementValue.getContent());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
