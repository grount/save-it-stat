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
                    handleCheckBoxChange={null}
                    isChecked={this.props.isChecked}
                    key={this.props.id}
                />
            </ListGroupItem>
        );
    }
}