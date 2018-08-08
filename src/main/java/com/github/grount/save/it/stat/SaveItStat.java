package com.github.grount.save.it.stat;

import org.json.JSONObject;

public class SaveItStat {
    private static final FileBase fileBase =
            new FileBase(Constants.FILE_EXISTENCE_LOCATION, JSONObject::new);

    public static void main(String[] args) {
    }

    public static void initalize() {
        String key = "is.elements.exists";
        String value = "0";
        Utils.createJsonWithField(fileBase, key, value);
    }
}
