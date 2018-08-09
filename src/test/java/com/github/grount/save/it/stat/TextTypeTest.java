package com.github.grount.save.it.stat;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextTypeTest {

    @Test
    @DisplayName("Given a type it should generate json element properly")
    void convertToJSON_fromClassFields() {
        Type type = new TextType("title", "content");
        convertToJSON_fromClassFieldsHelper(type);
    }

    public void convertToJSON_fromClassFieldsHelper(Type type) {
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("type", type.getType());
        expectedJson.put("title", "title");
        expectedJson.put("content", "content");
        expectedJson.put("id", type.getJsonObject().get("id"));

        assertEquals(type.convertToJson(), expectedJson.toString());
    }
}