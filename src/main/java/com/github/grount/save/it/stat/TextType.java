package com.github.grount.save.it.stat;

import org.json.JSONObject;

class TextType extends Type {
    private static String type = "text";

    TextType(String title, String content) {
        super(type, title, content);
        initializeJsonObject(title, content);
    }

    @Override
    void initializeJsonObject(String title, String content) {
        JSONObject json = getJsonObject();
        json.put("title", title);
        json.put("content", content);
    }

    @Override
    String convertToJson() {
        return getJsonObject().toString();
    }
}
