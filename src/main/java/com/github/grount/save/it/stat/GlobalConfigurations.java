package com.github.grount.save.it.stat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class GlobalConfigurations {
    private static final Set<String> configurations = new HashSet<>(Arrays.asList("default.location"));
    private static final FileBase propertiesBase = new FileBase(Constants.CONFIG_LOCATION, Properties::new);

    private GlobalConfigurations() {
    }

    public static void setDefaultConfigurations() {
        if (isPathDoesNotExists())
            createDefaultConfigurations();
    }

    private static boolean isPathDoesNotExists() {
        return !propertiesBase.getPath().toFile().exists();
    }

    private static void createDefaultConfigurations() {
        setConfigurationValue("default.location", System.getProperty("user.home"));
    }

    public static void setConfigurationValue(String key, String value) {
        if (configurations.contains(key))
            Utils.setFieldOfPropertiesFile(propertiesBase, key, value);
        else
            throw new IllegalArgumentException("Please set valid configuration key");
    }
}
