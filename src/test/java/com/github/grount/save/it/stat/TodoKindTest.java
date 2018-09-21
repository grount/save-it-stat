package com.github.grount.save.it.stat;

import org.junit.jupiter.api.Test;

class TodoKindTest {

    @Test
    void convertToJson_shouldCreateValidJson() {
        TextKindTest textTypeTest = new TextKindTest();
        Kind kind = new TodoKind("title", "content");
        textTypeTest.convertToJSON_fromClassFieldsHelper(kind);
    }
}