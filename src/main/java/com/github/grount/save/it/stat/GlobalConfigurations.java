package com.github.grount.save.it.stat;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class GlobalConfigurations {
    private static final String PROPERTIES_LOCATION = "config.properties";
    private static final Path path = Paths.get(PROPERTIES_LOCATION);
    private static final LogManager logManager = LogManager.getLogManager();
    private static final Logger logger = logManager.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final Set<String> configurations = new HashSet<>(Arrays.asList("default.location"));
    private static Properties properties = new Properties();

    private GlobalConfigurations() {
    }

    public static void setDefaultConfigurations() {
        if (isPathDoesNotExists())
            createDefaultConfigurations();
    }

    private static boolean isPathDoesNotExists() {
        return !path.toFile().exists();
    }

    private static void createDefaultConfigurations() {
        setConfigurationValue("default.location", System.getProperty("user.home"));
    }

    public static void setConfigurationValue(String key, String value) {
        if (configurations.contains(key))
            setValidConfigurationValue(key, value);
         else
             throw new IllegalArgumentException("Please set valid configuration key");
    }

    private static void setValidConfigurationValue(String key, String value) {
        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            properties.setProperty(key, value);
            properties.store(bw, null);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Unable to save global configurations: {0}", e.getMessage());
        }
    }
}
