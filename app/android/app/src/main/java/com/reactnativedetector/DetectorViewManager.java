package com.reactnativedetector;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.github.asmgf.reactnativedetector.detector.DetectorView;

public class DetectorViewManager extends SimpleViewManager<DetectorView> {
    @Override
    public String getName() {
        return "ReactNativeDetectorView";
    }

    @Override
    protected DetectorView createViewInstance(ThemedReactContext context) {
        return new DetectorView(context, DetectorView.CAMERA_ID_BACK);
    }

    @ReactProp(name = "enabled")
    public void setEnabled(DetectorView view, boolean enabled) {
        if (enabled) {
            view.enableView();
        } else {
            view.disableView();
        }
    }
}
