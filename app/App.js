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
import DetectorView from './DetectorView';

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
    this.state = {hasPermissions: null, detected: false};
  }

  async componentWillMount() {
    this.setState({hasPermissions: await requestPermissions()});
  }

  render() {
    return (
      <View style={styles.container}>
        <DetectorView style={styles.detector} />
        {this.state.hasPermissions === false &&
          <Text style={styles.noPermissions}>Camera access is not allowed</Text>}
        {this.state.detected && <View style={styles.cue} />}
      </View>
    );
  }
}

const overlayStyles = {
  position: 'absolute',
  width: '100%',
  height: '100%',
  backgroundColor: 'transparent',
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'black',
  },
  noPermissions: {
    color: 'red',
    fontSize: 20,
    textAlign: 'center',
  },
  detector: {
    ...overlayStyles,
  },
  cue: {
    borderColor: 'red',
    borderWidth: 5,
    ...overlayStyles,
  },
});
