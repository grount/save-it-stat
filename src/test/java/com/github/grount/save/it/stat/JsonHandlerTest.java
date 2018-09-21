package com.github.grount.save.it.stat;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.github.grount.save.it.stat.Constants.ELEMENTS_NAME;
import static com.github.grount.save.it.stat.Constants.ELEMENTS_PATH;
import static org.junit.jupiter.api.Assertions.*;

class JsonHandlerTest {
    private static Kind kind;
    private static Path path;

    @BeforeAll
    static void initAll() {
        kind = new TextKind("myTitle", "Awesome content");
        path = Paths.get(ELEMENTS_PATH);
    }

    @Test
    void getJsonObject_ifCachedDataIsNotEmpty_loadAndMergeElements() throws IOException {
        TestUtils.deleteIfFilesExists(path);
        JsonGenerator.generate(kind);

        String loadedJsonString = new String(Files.readAllBytes(path));
        JSONObject loadedJson = new JSONObject(loadedJsonString);
        Kind newKind = new TextKind("secondTitle", "content");
        JSONArray innerArray = loadedJson.getJSONArray(ELEMENTS_NAME);
        innerArray.put(newKind.getJsonObject());
        JSONObject newParent = new JSONObject().put(ELEMENTS_NAME, innerArray);
        assertTrue(newParent.toString().equals(JsonHandler.getJsonObject(newKind).toString()));
    }
}