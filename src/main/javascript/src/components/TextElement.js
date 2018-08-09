import React, { Component } from 'react';
import { ListGroupItem, ListGroupItemHeading, ListGroupItemText } from 'reactstrap';

export default class Element extends Component {
    render() {
        return (
            <ListGroupItem>
                <ListGroupItemHeading>{this.props.title}</ListGroupItemHeading>
                <ListGroupItemText>{this.props.text}</ListGroupItemText>
            </ListGroupItem>
        );
    }
}