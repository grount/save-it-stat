import React, { Component } from 'react';
import Element from './Element';
import { ListGroup, Container } from 'reactstrap';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      json: this.tryRequireElements()
    }
  }

  tryRequireElements = () => {
    return require('../elements.json');
  }

  isEmpty = (obj) => {
        // null and undefined are "empty"
        if (obj == null) return true;

        // Assume if it has a length property with a non-zero value
        // that that property is correct.
        if (obj.length > 0)    return false;
        if (obj.length === 0)  return true;
    
        // If it isn't an object at this point
        // it is empty, but it can't be anything *but* empty
        // Is it empty?  Depends on your application.
        if (typeof obj !== "object") return true;
    
        // Otherwise, does it have any properties of its own?
        // Note that this doesn't handle
        // toString and valueOf enumeration bugs in IE < 9
        for (var key in obj) {
            if (hasOwnProperty.call(obj, key)) return false;
        }
    
        return true;
  }

  generateElements = () => {
    const elements = !this.isEmpty(this.state.json) ? this.state.json.elements.map(element => {
      return <Element
        key={element.id}
        title={element.title}
        text={element.content}
      />
    }) : null;
    return elements;
  }

  render() {
    return (
      <Container>
        <ListGroup>
          {this.generateElements()}
        </ListGroup>
      </Container>
    );
  }
}

export default App;
