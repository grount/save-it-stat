package com.github.grount.save.it.stat;

import org.json.JSONObject;

import java.util.UUID;

public abstract class Type {
    private String title;
    private String content;
    private UUID uuid;
    private JSONObject jsonObject;

    public Type(String title, String content) {
        this.title = title;
        this.content = content;
        this.uuid = UUID.randomUUID();
        this.jsonObject = new JSONObject();
    }

    public abstract String convertToJson();

    JSONObject getJsonObject() {
        return jsonObject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UUID getUuid() {
        return uuid;
    }
}
