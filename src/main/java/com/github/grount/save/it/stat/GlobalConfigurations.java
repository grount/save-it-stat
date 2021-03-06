package com.github.grount.save.it.stat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

class GlobalConfigurations {
    private static final Set<String> configurations = new HashSet<>(Arrays.asList("default.location"));
    private static final FileBase<Properties> propertiesBase = new FileBase<>(Constants.CONFIG_LOCATION, Properties::new);

    private GlobalConfigurations() {
        throw new AssertionError();
    }

    static void setDefaultConfigurations() {
        if (isPathDoesNotExists())
            createDefaultConfigurations();
    }

    private static boolean isPathDoesNotExists() {
        return !propertiesBase.getPath().toFile().exists();
    }

    private static void createDefaultConfigurations() {
        setConfigurationValue("default.location", System.getProperty("user.home"));
    }

    static void setConfigurationValue(String key, String value) {
        if (configurations.contains(key))
            Utils.setFieldOfPropertiesFile(propertiesBase, key, value);
        else
            throw new IllegalArgumentException("Please set valid configuration key");
    }
}
