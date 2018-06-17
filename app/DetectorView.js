import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {requireNativeComponent, ViewPropTypes} from 'react-native';

const ReactNativeDetectorView = requireNativeComponent(
    'ReactNativeDetectorView',
    {
        name: 'DetectorView',
        propTypes: {
            enabled: PropTypes.bool,
            ...ViewPropTypes,
        },
    },
);

export default class DetectorView extends Component {
    render() {
        return (
            <ReactNativeDetectorView {...this.props} />
        );
    }
}
