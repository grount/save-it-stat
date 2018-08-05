package com.github.grount.save.it.stat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonGeneratorTest {
    private static Type type;
    private static Path path;

    @BeforeAll
    static void initAll() {
        type = new Text("myTitle", "Awesome content");
        path = Paths.get("elements.json");
    }


    @Test
    @DisplayName("Generate function creates json if not exists")
    void generate_ifJsonDidNotExists_createIt() {
        deleteFileIfExists();
        generateAndAssertFileExists();
    }

    @Test
    @DisplayName("Generate function creates json with correct fields")
    void generate_createsJson_withFieldsExists() {
        deleteFileIfExists();
        generateAndAssertFileExists();
        assertTrue(getFileContent().contains(type.convertToJson()));
    }

    private String getFileContent() {
        List<String> lines = null;

        try {
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines != null ? lines.toString() : "";
    }

    private void deleteFileIfExists() {
        if (path.toFile().exists())
            path.toFile().delete();
    }

    @Test
    @DisplayName("Generate function appends to json if it exists")
    void generate_ifJsonExists_appendToIt() {
        Type newType = new Text("Another title", "Even better content");
        deleteFileIfExists();
        JsonGenerator.generate(type);
        JsonGenerator.generate(newType);
        String fileContent = getFileContent();

        assertAll(() -> assertTrue(fileContent.contains(type.convertToJson())),
                () -> assertTrue(fileContent.contains(newType.convertToJson()))
        );
    }

    private void generateAndAssertFileExists() {
        JsonGenerator.generate(type);
        assertTrue(Paths.get("elements.json").toFile().exists());
    }
}