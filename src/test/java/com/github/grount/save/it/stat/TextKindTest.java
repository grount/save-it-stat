package com.github.grount.save.it.stat;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TextKindTest {

    @Test
    @DisplayName("Given a type it should generate json element properly")
    void convertToJSON_fromClassFields() {
        Kind kind = new TextKind("title", "content");
        convertToJSON_fromClassFieldsHelper(kind);
    }

    public void convertToJSON_fromClassFieldsHelper(Kind kind) {
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("kind", kind.getTaskType());
        expectedJson.put("title", "title");
        expectedJson.put("content", "content");
        expectedJson.put("id", kind.getJsonObject().get("id"));

        assertEquals(kind.convertToJson(), expectedJson.toString());
    }
}