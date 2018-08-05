package com.github.grount.save.it.stat;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextTest {

    @Test
    void convertToJSON_fromClassFields() {
        Type type = new Text("title", "content");
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("title", "title");
        expectedJson.put("content", "content");
        expectedJson.put("id", type.getJsonObject().get("id"));

        assertEquals(type.convertToJson(), expectedJson.toString());
    }
}