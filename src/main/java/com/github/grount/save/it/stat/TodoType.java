package com.github.grount.save.it.stat;

import org.json.JSONObject;

class TodoType extends Type {
    private static String type = "todo";

    TodoType(String title, String content) {
        super(type, title, content);
        initializeJsonObject(title, content);
    }

    @Override
    String convertToJson() {
        return getJsonObject().toString();
    }

    @Override
    void initializeJsonObject(String title, String content) {
        JSONObject json = getJsonObject();
        json.put("title", title);
        json.put("content", content);
    }
}
