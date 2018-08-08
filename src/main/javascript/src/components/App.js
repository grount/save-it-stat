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
    const file_existence = require('../file-existence.json');
    if (file_existence["is.elements.exists"] === "1") {
      return require('../elements.json');
    } else {
      return null;
    }
  }

  generateElements = () => {
    const elements = this.state.json ? this.state.json.elements.map(element => {
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
