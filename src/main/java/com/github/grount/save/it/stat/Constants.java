package com.github.grount.save.it.stat;

class Constants {
    static final String DEFAULT_PATH = "src//main//javascript//src//";
    static final String FILE_EXISTENCE_NAME = "file-existence.json";
    static final String FILE_EXISTENCE_LOCATION = DEFAULT_PATH + FILE_EXISTENCE_NAME;
    static final String CONFIG_NAME = "config.properties";
    static final String CONFIG_LOCATION = DEFAULT_PATH + CONFIG_NAME;
    static final String ELEMENTS_NAME = "elements";
    static final String ELEMENTS_NAME_JSON = ELEMENTS_NAME + ".json";
    static final String ELEMENTS_PATH = DEFAULT_PATH + ELEMENTS_NAME_JSON;
    static final String IS_ELEMENTS_EXISTS = "is.elements.exists";

    private Constants() {
        throw new AssertionError();
    }
}
