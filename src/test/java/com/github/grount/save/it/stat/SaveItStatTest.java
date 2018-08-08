package com.github.grount.save.it.stat;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class SaveItStatTest {
    private Path path = Paths.get(Constants.FILE_EXISTENCE_LOCATION);

    @Test
    @DisplayName("Entry point should initialize a text file that says if properties created")
    void initialize_ifDoesNotExists_createIsExistsFile() {
        SaveItStat.initalize();
        assertTrue(path.toFile().exists());
    }

    @Test
    @DisplayName("file-existence default value of property is.elements.exists = 0")
     void initialize_ifCreated_defaultPropertyIsFalse() throws IOException {
        TestUtils.deleteIfFilesExists(path);
        SaveItStat.initalize();
        String jsonContent  = new String(Files.readAllBytes(path));
        JSONObject jsonObject = new JSONObject(jsonContent);
        assertEquals(jsonObject.get("is.elements.exists"), "0");
    }
}