package com.github.grount.save.it.stat;

import org.json.JSONObject;

public class Text extends Type {
    private JSONObject json = new JSONObject();

    public Text(String title, String content) {
        super(title, content);
        json.put("title", title);
        json.put("content", content);
    }

    @Override
    public String convertToJSON() {
        return json.toString();
    }
}
