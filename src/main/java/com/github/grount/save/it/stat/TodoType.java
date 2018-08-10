package com.github.grount.save.it.stat;

import org.json.JSONObject;

public class TodoType extends Type {
    private static String type = "todo";

    public TodoType(String title, String content) {
        super(type, title, content);
        initializeJsonObject(title, content);
    }

    @Override
    public String convertToJson() {
        return getJsonObject().toString();
    }

    @Override
    protected void initializeJsonObject(String title, String content) {
        JSONObject json = getJsonObject();
        json.put("title", title);
        json.put("content", content);
    }
}
