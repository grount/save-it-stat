package com.github.grount.save.it.stat;

import org.json.JSONObject;

class TextKind extends Kind {
    private static String kind = "text";

    TextKind(String title, String content) {
        super(kind, title, content);
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
