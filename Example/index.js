/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  View,
  TouchableHighlight
} from 'react-native';
import ActionSheet from '@yfuks/react-native-action-sheet';

var BUTTONS = [
  'Option 0',
  'Option 1',
  'Option 2',
  'Delete',
  'Cancel',
];

var DESTRUCTIVE_INDEX = 3;
var CANCEL_INDEX = 4;

export default class Example extends Component {
  state = {
    clicked: 'none',
  };

  render() {
    return (
      <View style={styles.container}>
        <Text onPress={this.showActionSheet} style={styles.button}>
          Click to show the ActionSheet
        </Text>
        <Text>
          Clicked button: {this.state.clicked}
        </Text>
      </View>
    );
  }

  showActionSheet = () => {
    ActionSheet.showActionSheetWithOptions({
      options: BUTTONS,
      cancelButtonIndex: CANCEL_INDEX,
      destructiveButtonIndex: DESTRUCTIVE_INDEX,
      tintColor: 'blue'
    },
    (buttonIndex) => {
      this.setState({ clicked: BUTTONS[buttonIndex] });
    });
  };
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  button: {
    marginBottom: 10,
    fontWeight: '500',
  }
});

AppRegistry.registerComponent('Example', () => Example);
