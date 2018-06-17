/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  PermissionsAndroid,
  StyleSheet,
  Text,
  View
} from 'react-native';

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' +
    'Cmd+D or shake for dev menu',
  android: 'Double tap R on your keyboard to reload,\n' +
    'Shake or press menu button for dev menu',
});

async function requestPermissions() {
  if (Platform.OS === 'android') {
    const granted = await PermissionsAndroid.request(PermissionsAndroid.PERMISSIONS.CAMERA);
    return Platform.Version >= 23 ? granted === PermissionsAndroid.RESULTS.GRANTED : granted;
  }
  return true;
}

export default class App extends Component {
  constructor(props) {
    super(props);
    this.state = {hasPermissions: null};
  }

  async componentWillMount() {
    this.setState({hasPermissions: await requestPermissions()});
  }

  render() {
    if (this.state.hasPermissions === false) {
        return (
        <View style={styles.container}>
          <Text style={styles.noPermissions}>Camera access is not allowed</Text>
        </View>
      );
    }
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>
          Welcome to React Native!
        </Text>
        <Text style={styles.instructions}>
          To get started, edit App.js
        </Text>
        <Text style={styles.instructions}>
          {instructions}
        </Text>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  noPermissions: {
    color: 'red',
    fontSize: 20,
    textAlign: 'center',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
