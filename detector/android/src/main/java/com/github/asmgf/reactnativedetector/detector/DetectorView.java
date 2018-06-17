package com.github.asmgf.reactnativedetector.detector;

import android.content.Context;
import android.util.AttributeSet;

import org.opencv.android.JavaCameraView;
import org.opencv.core.Mat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class DetectorView extends JavaCameraView {
    private final Processor processor = new Processor();

    public DetectorView(Context context, int cameraId) {
        super(context, cameraId);
        setCvCameraViewListener(processor);
    }

    public DetectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCvCameraViewListener(processor);
    }

    public void setListener(Listener listener) {
        processor.setListener(listener);
    }

    public interface Listener {
        void onResult(boolean detected);
    }

    private static class Processor implements CvCameraViewListener2 {
        final AtomicBoolean busy = new AtomicBoolean();
        final Detector detector = new Detector();
        volatile ExecutorService executor;
        volatile Listener listener;

        void setListener(Listener listener) {
            this.listener = listener;
        }

        @Override
        public void onCameraViewStarted(int width, int height) {
            executor = Executors.newSingleThreadExecutor();
        }

        @Override
        public void onCameraViewStopped() {
            executor.shutdown();
        }

        @Override
        public Mat onCameraFrame(CvCameraViewFrame frame) {
            Mat rgbaMat = frame.rgba();
            tryProcess(rgbaMat);
            return rgbaMat;
        }

        void tryProcess(Mat rgbaMat) {
            if (busy.compareAndSet(false, true)) {
                final Mat rgbaCopyMat = rgbaMat.clone();
                executor.submit(new Runnable() {
                    @Override
                    public void run() {
                        process(rgbaCopyMat);
                        rgbaCopyMat.release();
                        busy.set(false);
                    }
                });
            }
        }

        void process(Mat rgbaMat) {
            Listener listener = this.listener;
            if (listener != null) {
                boolean detected = detector.detect(rgbaMat);
                listener.onResult(detected);
            }
        }
    }
}
