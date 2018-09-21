package com.github.grount.save.it.stat;

import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Map;

import static com.github.grount.save.it.stat.ArgumentsConverter.checkFormValidity;
import static com.github.grount.save.it.stat.ArgumentsConverter.convert;
import static org.junit.jupiter.api.Assertions.*;

class ArgumentsConverterTest {
    private String[] validFullArguments = {"--title=title", "--content=content"};
    private String[] invalidFullArguments = {"--title=title", "--contant=content"};
    private String[] validShortArguments = {"-t=title", "-c=content"};
    private String[] invalidShortArguments = {"-f=t", "-c=content"};
    private String[] validMixedArguments = {"-t=title", "--content=content"};
    private String[] validForm = {"-k=value 342", "--y=val"};
    private String[] invalidForm = {"title=title", "--content=content", "-test=t"};

    @Test
    @DisplayName("Valid full name arguments should not throw exception")
    void convert_validFullArguments_ExceptionNotThrown() {
        assertDoesNotThrow(() -> convert(validFullArguments));
        assertDoesNotThrow(() -> convert(validShortArguments));
        assertDoesNotThrow(() -> convert(validMixedArguments));
    }

    @Test
    @DisplayName("Invalid full name arguments should throw exception")
    void convert_invalidFullArguments_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> convert(invalidFullArguments));
        assertThrows(IllegalArgumentException.class, () -> convert(invalidShortArguments));
    }

    @Test
    @DisplayName("Arguments with valid form, starts with -- or - should not throw exception")
    void checkFormValidity_validForm_ExceptionNotThrown() {
        assertDoesNotThrow(() -> checkFormValidity(validForm));
    }

    @Test
    @DisplayName("Arguments with invalid form, that doesn't start with -- or - should throw exception")
    void checkFormValidity_invalidForm_ExceptionThrown() {
        assertThrows(IllegalArgumentException.class, () -> checkFormValidity(invalidForm));
    }


    @Nested
    @DisplayName("Test convert helper class")
    class testConvertHelper {
        private Map<String,String> argumentsMap;
        @BeforeEach
        void initAll() {
            argumentsMap = new HashMap<>();
            argumentsMap.put("content", "content");
            argumentsMap.put("title", "title");
        }

        @Test
        @DisplayName("Convert full arguments should return a valid map")
        void convert_givenFullArguments_returnValidMap() {
            assertTrue(convert(validFullArguments).equals(argumentsMap));
        }

        @Test
        @DisplayName("Convert short arguments should return a valid map")
        void convert_givenShortArguments_returnValidMap() {
            Map<String, String> map = convert(validShortArguments);
            boolean state = map.equals(argumentsMap);
            assertTrue(state);
        }

        @Test
        @DisplayName("Convert mixed arguments should return a valid map")
        void convert_givenMixedArguments_returnValidMap() {
            assertTrue(convert(validMixedArguments).equals(argumentsMap));
        }
    }
}