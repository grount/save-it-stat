package com.github.grount.save.it.stat;

import org.json.JSONObject;

import java.util.UUID;

abstract class Type {
    private String title;
    private String content;
    private String taskType;
    private UUID uuid;
    private JSONObject jsonObject;

    Type(String taskType, String title, String content) {
        this.taskType = taskType;
        this.title = title;
        this.content = content;
        this.uuid = UUID.randomUUID();
        initializeJsonObject();
    }

    private void initializeJsonObject() {
        this.jsonObject = new JSONObject();
        this.jsonObject.put("type", taskType);
        this.jsonObject.put("id", uuid);
    }

    abstract String convertToJson();

    abstract void initializeJsonObject(String title, String content);

    JSONObject getJsonObject() {
        return jsonObject;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getContent() {
        return content;
    }

    void setContent(String content) {
        this.content = content;
    }

    UUID getUuid() {
        return uuid;
    }

    String getTaskType() {
        return taskType;
    }
}
