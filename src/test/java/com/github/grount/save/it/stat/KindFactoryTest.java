package com.github.grount.save.it.stat;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KindFactoryTest {

    @Test
    void createType() {
        assertAll(
                () -> TypeFactory.createType("text", "title", "content")
                        .getClass()
                        .isInstance(TextKind.class),
                () -> TypeFactory.createType("todo", "title", "content")
                        .getClass()
                        .isInstance(TodoKind.class),
                () -> assertTrue(
                        TypeFactory.createType("wrong-type", "title", "content") == null)
        );
    }
}