import React, { Component } from 'react';
import Element from './Element';
import { ListGroup, Container } from 'reactstrap';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      json: this.tryRequireJson()
    }
  }

  tryRequireJson = () => {
    try {
      return require('../elements.json');
    } catch (err) {
      return null;
    }
  }

  generateElements = () => {
    const elements = this.state.json ? this.state.json.elements.map(element => {
      return <Element
        id={element.id}
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
