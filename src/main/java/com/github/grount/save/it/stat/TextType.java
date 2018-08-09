package com.github.grount.save.it.stat;

import org.json.JSONObject;

public class TextType extends Type {
    private static String type = "text";

    public TextType(String title, String content) {
        super(type, title, content);
        initializeJsonObject(title, content);
    }

    @Override
    protected void initializeJsonObject(String title, String content) {
        JSONObject json = getJsonObject();
        json.put("title", title);
        json.put("content", content);
    }

    @Override
    public String convertToJson() {
        return getJsonObject().toString();
    }
}
