package com.github.grount.save.it.stat;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;

import static com.github.grount.save.it.stat.Constants.ELEMENTS_NAME;

class JsonGenerator {
    private static final FileBase<JSONObject> fileBase = new FileBase(Constants.ELEMENTS_PATH, JSONObject::new);
    private static Type type;

    private JsonGenerator() {
        throw new AssertionError();
    }

    static void generate(@Nonnull Type type) {
        JsonGenerator.type = type;

        if (fileBase.getPath().toFile().exists())
            appendTypeToExistingJson();
        else
            createJsonWithType(type.getJsonObject());
    }

    private static void createJsonWithType(JSONObject objectToWrite) {
        try (BufferedWriter bw = Files.newBufferedWriter(fileBase.getPath())) {
            JSONObject parent = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(objectToWrite);
            parent.put(ELEMENTS_NAME, jsonArray);
            bw.write(parent.toString());
        } catch (IOException e) {
            fileBase.getLogger().log(Level.SEVERE, "Cannot save type to json: {0}", e.getMessage());
        }
    }

    private static void appendTypeToExistingJson() {
        try {
            String json = new String(Files.readAllBytes(fileBase.getPath()));
            JSONObject jsonObject = new JSONObject(json);
            appendToJson(jsonObject);
        } catch (IOException e) {
            fileBase.getLogger().log(Level.SEVERE, "Cannot append type to json: {0}", e.getMessage());
        }
    }

    private static void appendToJson(JSONObject jsonObject) throws IOException {
        JSONArray jsonArray = jsonObject.getJSONArray(ELEMENTS_NAME);
        jsonArray.put(type.getJsonObject());

        try (BufferedWriter bw = Files.newBufferedWriter(fileBase.getPath())) {
            JSONObject parent = new JSONObject();
            parent.put(ELEMENTS_NAME, jsonArray);
            bw.write(parent.toString());
        }
    }
}
