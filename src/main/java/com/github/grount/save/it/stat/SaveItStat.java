package com.github.grount.save.it.stat;

import org.json.JSONObject;

public class SaveItStat {
    private static final FileBase fileBase =
            new FileBase(Constants.FILE_EXISTENCE_LOCATION, JSONObject::new);

    public static void main(String[] args) {
        initialize();
    }

    public static void initialize() {
        String value = "0";
        Utils.createJsonWithField(fileBase, Constants.IS_ELEMENTS_EXISTS, value);
    }
}
