package com.github.grount.save.it.stat;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.logging.Level;

import static com.github.grount.save.it.stat.Constants.ELEMENTS_NAME;
import static com.github.grount.save.it.stat.Constants.ELEMENTS_NAME_JSON;

public class JsonHandler {
    private static final FileBase<JSONObject> fileBase = new FileBase<>(Constants.ELEMENTS_PATH, JSONObject::new);
    private static boolean elementsExist;

    private JsonHandler() {
        throw new AssertionError();
    }

    public static boolean isElementsExist() {
        return elementsExist;
    }

    static JSONObject getJsonObject(@Nonnull final Kind kind) {
        Objects.requireNonNull(kind);
        return dataCachedInFile() ? loadAndMergeDataWithCurrentKind(kind) : createProperKind(kind);
    }

    private static JSONObject loadAndMergeDataWithCurrentKind(final Kind kind) {
        elementsExist = true;
        JSONArray loadedJson = loadExistingJson();
        JSONObject parent = new JSONObject();

        if (loadedJson.length() > 0) {
            loadedJson.put(kind.getJsonObject());
            parent.put(ELEMENTS_NAME, loadedJson);
        } else
            fileBase.getLogger().log(Level.SEVERE,"Failed to load existing json and exception has not caught.");

        return parent;
    }

    /**
     * Loads the ELEMENTS_NAME file
     * @return an empty JSONArray if failed to load existing JSON
     */
    private static JSONArray loadExistingJson() {
        try {
            String json = new String(Files.readAllBytes(fileBase.getPath()));
            JSONObject jsonObject = new JSONObject(json);
            return jsonObject.getJSONArray(ELEMENTS_NAME);
        } catch (IOException e) {
            fileBase.getLogger().log(Level.SEVERE, "Cannot load {0} file.", ELEMENTS_NAME_JSON);
        }
        return new JSONArray();
    }

    private static JSONObject createProperKind(final Kind kind) {
        JSONObject parent = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(kind.getJsonObject());
        parent.put(ELEMENTS_NAME, jsonArray);
        return parent;
    }

    private static boolean dataCachedInFile() {
        File file = fileBase.getPath().toFile();
        return file.exists() && file.isFile();
    }

    static void deleteElementsFile() {
        if (JsonHandler.isElementsExist())
            fileBase.getPath().toFile().delete();
    }

    public static void appendNewKindToFile(Kind kind) {
    }
}
