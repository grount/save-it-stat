import React, { Component } from 'react';
import Element from './Element';
import { ListGroup, Container } from 'reactstrap';

class App extends Component {
  constructor(props) {
    super(props);
    this.state = {
      json: require('../elements.json')
    }
  }

  generateElements = () => {
    const elements = this.state.json.elements.map(element => {
      return <Element
        id={element.id}
        title={element.title}
        text={element.content}
      />
    });
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
