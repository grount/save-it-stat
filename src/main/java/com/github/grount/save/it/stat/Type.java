package com.github.grount.save.it.stat;

import org.json.JSONObject;

import java.util.UUID;

public abstract class Type {
    private String title;
    private String content;
    private String type;
    private UUID uuid;
    private JSONObject jsonObject;

    public Type(String type, String title, String content) {
        this.type = type;
        this.title = title;
        this.content = content;
        this.uuid = UUID.randomUUID();
        initializeJsonObject();
    }

    private void initializeJsonObject() {
        this.jsonObject = new JSONObject();
        this.jsonObject.put("type", type);
        this.jsonObject.put("id", uuid);
    }

    public abstract String convertToJson();

    protected abstract void initializeJsonObject(String title, String content);

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

    public String getType() {
        return type;
    }
}
