package com.github.grount.save.it.stat;

import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
    void initialize_ifCreated_defaultPropertyIsFalse() {
        TestUtils.deleteIfFilesExists(path);
        SaveItStat.initalize();
        String fileContent = TestUtils.getFileContent(path);
        JSONObject comparisionJson = new JSONObject();
        comparisionJson.put("is.elements.exists", "0");
        assertTrue(fileContent.contains(comparisionJson.toString()));
    }
}