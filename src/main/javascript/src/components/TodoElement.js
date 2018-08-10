import React, { Component } from 'react';
import { ListGroupItem, ListGroupItemHeading } from 'reactstrap';
import Checkbox from './Checkbox';

export default class TodoElement extends Component {
    render() {
        console.log(this.props);
        return (
            <ListGroupItem>
                <ListGroupItemHeading>{this.props.title}</ListGroupItemHeading>
                <Checkbox
                    content={this.props.content}
                    key={this.props.id}
                    id={this.props.id}
                />
            </ListGroupItem>
        );
    }
}