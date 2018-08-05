package com.github.grount.save.it.stat;

import org.json.JSONObject;

public class Text extends Type {
    private JSONObject json;

    public Text(String title, String content) {
        super(title, content);
        json = getJsonObject();
        json.put("title", title);
        json.put("content", content);
        json.put("id", getUuid());
    }

    @Override
    public String convertToJson() {
        return json.toString();
    }
}
