package com.github.grount.save.it.stat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GlobalConfigurationsTest {
    static InputStream inputStream;
    static Properties properties;
    final String defaultLocation = "default.location";

    @BeforeAll
    static void initAll() throws IOException {
        inputStream = new FileInputStream("config.properties");
        properties = new Properties();
    }

    @Test
    @DisplayName("Test global configuration output default location")
    void setDefaultConfigurations_defaultLocation_Exists() throws IOException {
        GlobalConfigurations.setDefaultConfigurations();

        properties.load(inputStream);
        String value = properties.getProperty(defaultLocation);

        assertTrue(value != null);
    }

    @Test
    @DisplayName("Test if given global configuration is set properly")
    void setConfigurationValue_setsGlobalConfiguration_ifKeyExists() throws IOException {
        String value = "some_value";
        GlobalConfigurations.setConfigurationValue(defaultLocation, value);
        properties.load(inputStream);

        String defaultLocationProperty = properties.getProperty(defaultLocation);
        assertTrue(defaultLocationProperty.equals(value));
    }

    @Test
    @DisplayName("Test if wrong global configuration parameter is passed, it should throw exception")
    void setConfigurationValue_ThrowsException_ifWrongKeyIsPassed() {
        assertThrows(IllegalArgumentException.class,
                () -> GlobalConfigurations.setConfigurationValue("non-existing.property", "value"));
    }
}