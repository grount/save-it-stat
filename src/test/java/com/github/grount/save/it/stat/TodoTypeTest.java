package com.github.grount.save.it.stat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTypeTest {

    @Test
    void convertToJson_shouldCreateValidJson() {
        TextTypeTest textTypeTest = new TextTypeTest();
        Type type = new TodoType("title", "content");
        textTypeTest.convertToJSON_fromClassFieldsHelper(type);
    }
}