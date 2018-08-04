package com.github.grount.save.it.stat;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;


public class JsonGenerator {
    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final String ELEMENTS_JSON = "elements.json";
    private static final String ELEMENTS_NAME = "elements";
    private static Path path;
    private static Type type;

    private JsonGenerator() {
    }

    public static void generate(@Nonnull Type type) {
        JsonGenerator.type = type;
        path = Paths.get(ELEMENTS_JSON);

        if (path.toFile().exists())
            appendTypeToExistingJson();
        else
            createJsonWithType(type.getJsonObject());
    }

    private static void createJsonWithType(JSONObject objectToWrite) {
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            JSONObject parent = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            jsonArray.put(objectToWrite);
            parent.put(ELEMENTS_NAME, jsonArray);
            bw.write(parent.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cannot save type to json: {0}", e.getMessage());
        }
    }

    private static void appendTypeToExistingJson() {
        try {
            String json = new String(Files.readAllBytes(path));
            JSONObject jsonObject = new JSONObject(json);
            appendToJson(jsonObject);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Cannot append type to json: {0}", e.getMessage());
        }
    }

    private static void appendToJson(JSONObject jsonObject) throws IOException {
        JSONArray jsonArray = jsonObject.getJSONArray(ELEMENTS_NAME);
        jsonArray.put(type.getJsonObject());

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            JSONObject parent = new JSONObject();
            parent.put(ELEMENTS_NAME, jsonArray);
            bw.write(parent.toString());
        }
    }
}
