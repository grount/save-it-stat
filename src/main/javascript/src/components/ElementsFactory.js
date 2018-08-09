import React from 'react';
import TextElement from './TextElement';

export default class ElementsFactory{
    static build(element) {
        switch (element.type) {
            case 'text':
                return <TextElement
                    key={element.id}
                    title={element.title}
                    text={element.content}
                />
            default:
                return null;
        }
    }
}