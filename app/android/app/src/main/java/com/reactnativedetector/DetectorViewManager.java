package com.reactnativedetector;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.github.asmgf.reactnativedetector.detector.DetectorView;

import java.util.Map;

public class DetectorViewManager extends SimpleViewManager<DetectorView> {
    @Override
    public String getName() {
        return "ReactNativeDetectorView";
    }

    @Override
    protected DetectorView createViewInstance(ThemedReactContext context) {
        DetectorView view = new DetectorView(context, DetectorView.CAMERA_ID_BACK);
        view.setListener(new DetectorViewListener(context, view));
        return view;
    }

    @ReactProp(name = "enabled")
    public void setEnabled(DetectorView view, boolean enabled) {
        if (enabled) {
            view.enableView();
        } else {
            view.disableView();
        }
    }

    @Override
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.<String, Object>builder()
                .put("onNativeResult",
                        MapBuilder.of("phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onNativeResult")))
                .build();
    }

    private static class DetectorViewListener implements DetectorView.Listener {
        private final ReactContext context;
        private final DetectorView view;

        DetectorViewListener(ReactContext context, DetectorView view) {
            this.context = context;
            this.view = view;
        }

        @Override
        public void onResult(boolean detected) {
            WritableMap event = Arguments.createMap();
            event.putBoolean("detected", detected);
            context.getJSModule(RCTEventEmitter.class)
                    .receiveEvent(view.getId(), "onNativeResult", event);
        }
    }

}
