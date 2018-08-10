import React, { Component } from "react";

class Checkbox extends Component {
  state = {
    isChecked: false
  };

  componentDidMount = () => {
    this.refreshStateWithLocalStorage();

    window.addEventListener(
      "beforeunload",
      this.saveSetToLocalStorage.bind(this)
    )
  }

  componentWillMount = () => {
      this.selectedCheckboxes = new Set();
  }

  componentWillUnmount = () => {
    window.removeEventListener(
      "beforeunload",
      this.saveSetToLocalStorage.bind(this)
    );

    this.saveSetToLocalStorage();
  }
  
  refreshStateWithLocalStorage = () => {
    if (localStorage.hasOwnProperty(this.props.id)) {
      this.setState({
        isChecked: true
      });
    }
  }

  saveSetToLocalStorage = () => {
    localStorage.setItem(this.props.id, true);
  }

  toggleCheckBox = key => {
    if (this.selectedCheckboxes.has(key))
      this.selectedCheckboxes.delete(key);
    else
      this.selectedCheckboxes.add(key);
  }

  toggleCheckboxChange = () => {
    this.setState(({ isChecked }) => ({
      isChecked: !isChecked
    }));

    this.toggleCheckBox(this.props.id);
  };

  render() {
    return (
      <div className="checkbox">
        <label>
          <input
            type="checkbox"
            checked={this.state.isChecked}
            onChange={this.toggleCheckboxChange}
          />
          {this.props.content}
        </label>
      </div>
    );
  }
}

export default Checkbox;