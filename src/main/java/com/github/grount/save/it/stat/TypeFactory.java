package com.github.grount.save.it.stat;

class TypeFactory {
    private TypeFactory() {
    }

    static Kind createType(String type, String title, String content) {
        switch (type) {
            case "text":
                return new TextKind(title, content);
            case "todo":
                return new TodoKind(title, content);
            default:
                return null;
        }
    }
}
