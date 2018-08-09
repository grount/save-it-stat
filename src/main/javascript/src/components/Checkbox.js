import React, { Component } from "react";

class Checkbox extends Component {
  state = {
    isChecked: false
  };

  toggleCheckboxChange = () => {
    const { handleCheckboxChange, label } = this.props;

    this.setState(({ isChecked }) => ({
      isChecked: !isChecked
    }));

    handleCheckboxChange(label);
  };

  render() {
    return (
      <div className="checkbox">
        <label>
          <input
            type="checkbox"
            value={this.props.content}
            checked={this.props.isChecked}
            onChange={this.toggleCheckboxChange}
          />
          {this.props.content}
        </label>
      </div>
    );
  }
}

export default Checkbox;