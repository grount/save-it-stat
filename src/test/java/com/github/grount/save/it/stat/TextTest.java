package com.github.grount.save.it.stat;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextTest {

    @Test
    void convertToJSON_fromClassFields() {
        Type type = new Text("title", "content");
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("title", "title");
        expectedJson.put("content", "content");

        assertTrue(type.convertToJSON().equals(expectedJson.toString()));
    }
}