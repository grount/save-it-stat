package com.github.grount.save.it.stat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonGeneratorTest {
    private static Type type;
    private static Path path;

    @BeforeAll
    static void initAll() {
        type = new TextType("myTitle", "Awesome content");
        path = Paths.get(Constants.DEFAULT_PATH + "elements.json");
    }

    @Test
    @DisplayName("Generate function creates json if not exists")
    void generate_ifJsonDidNotExists_createIt() {
        TestUtils.deleteIfFilesExists(path);
        generateAndAssertFileExists();
    }

    @Test
    @DisplayName("Generate function creates json with correct fields")
    void generate_createsJson_withFieldsExists() {
        TestUtils.deleteIfFilesExists(path);
        generateAndAssertFileExists();
        assertTrue(TestUtils.getFileContent(path).contains(type.convertToJson()));
    }

    @Test
    @DisplayName("Generate function appends to json if it exists")
    void generate_ifJsonExists_appendToIt() {
        Type newType = new TextType("Another title", "Even better content");
        TestUtils.deleteIfFilesExists(path);
        JsonGenerator.generate(type);
        JsonGenerator.generate(newType);
        String fileContent = TestUtils.getFileContent(path);

        assertAll(() -> assertTrue(fileContent.contains(type.convertToJson())),
                () -> assertTrue(fileContent.contains(newType.convertToJson()))
        );
    }

    private void generateAndAssertFileExists() {
        JsonGenerator.generate(type);
        assertTrue(Paths.get(Constants.ELEMENTS_PATH).toFile().exists());
    }
}