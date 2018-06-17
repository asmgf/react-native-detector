import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {requireNativeComponent, ViewPropTypes} from 'react-native';

const ReactNativeDetectorView = requireNativeComponent(
    'ReactNativeDetectorView',
    {
        name: 'DetectorView',
        propTypes: {
            enabled: PropTypes.bool,
            onResult: PropTypes.func,
            ...ViewPropTypes,
        },
    },
);

export default class DetectorView extends Component {
    onNativeResult = (event) => {
        const listener = this.props.onResult;
        listener && listener(event.nativeEvent.detected);
    }

    render() {
        return (
            <ReactNativeDetectorView {...this.props} onNativeResult={this.onNativeResult} />
        );
    }
}
