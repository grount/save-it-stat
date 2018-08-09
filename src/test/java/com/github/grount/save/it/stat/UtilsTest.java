package com.github.grount.save.it.stat;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.github.grount.save.it.stat.Constants.FILE_EXISTENCE_LOCATION;
import static com.github.grount.save.it.stat.Constants.IS_ELEMENTS_EXISTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {
    private Path path = Paths.get(FILE_EXISTENCE_LOCATION);
    private FileBase fileBase = new FileBase(FILE_EXISTENCE_LOCATION, JSONObject::new);

    @Test
    @DisplayName("existence-file.json needs to exists")
    void existenceFileNeedsToExist() {
        assertTrue(path.toFile().exists());
    }

    @Test
    @DisplayName("Entry point should initialize a text file that says if properties created")
    void createJsonField_ofGivenPathAndField() throws IOException {
        TestUtils.deleteIfFilesExists(path);
        Utils.createJsonWithField(fileBase, IS_ELEMENTS_EXISTS, "0");
        String jsonContent = new String(Files.readAllBytes(path));
        JSONObject jsonObject = new JSONObject(jsonContent);
        assertEquals(jsonObject.get(IS_ELEMENTS_EXISTS), "0");
    }

    @Test
    @DisplayName("Toggle is.elements.exists from 0 to 1 if properties is added")
    void setJsonField_ifPropertiesChanged() throws IOException {
        createJsonField_ofGivenPathAndField();
        Utils.setJsonField(fileBase, IS_ELEMENTS_EXISTS, "1");
        String newJsonContent = new String(Files.readAllBytes(path));
        JSONObject newJsonObject = new JSONObject(newJsonContent);
        assertEquals(newJsonObject.get(IS_ELEMENTS_EXISTS), "1");
    }
}