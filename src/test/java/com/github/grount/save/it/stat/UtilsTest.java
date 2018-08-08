package com.github.grount.save.it.stat;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    @Test
    void setJsonField_ifPropertiesChanged() throws IOException{
        SaveItStatTest saveItStatTest = new SaveItStatTest();
        saveItStatTest.initialize_ifCreated_defaultPropertyIsFalse();

        String value = "1";
        Utils.setJsonField(new FileBase(Constants.FILE_EXISTENCE_LOCATION, JSONObject::new),
                Constants.IS_ELEMENTS_EXISTS, value);
        Path path = Paths.get(Constants.FILE_EXISTENCE_LOCATION);
        String newJsonContent = new String(Files.readAllBytes(path));
        JSONObject newJsonObject = new JSONObject(newJsonContent);
        assertEquals(newJsonObject.get(Constants.IS_ELEMENTS_EXISTS), value);
    }
}