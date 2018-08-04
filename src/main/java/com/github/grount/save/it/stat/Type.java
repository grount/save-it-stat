package com.github.grount.save.it.stat;

public abstract class Type {
    protected String title;
    protected String content;

    public Type(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public abstract String convertToJSON();

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
}
