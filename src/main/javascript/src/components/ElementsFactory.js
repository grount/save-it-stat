import React from "react";
import TextElement from "./TextElement";
import TodoElement from "./TodoElement";

export default class ElementsFactory {
  static build(element) {
    switch (element.type) {
      case "text":
        return (
          <TextElement
            key={element.id}
            title={element.title}
            text={element.content}
          />
        );
      case "todo":
        return (
          <TodoElement
            key={element.id}
            id={element.id}
            title={element.title}
            content={element.content}
            isChecked={false}
          />
        );
      default:
        return null;
    }
  }
}