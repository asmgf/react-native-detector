package com.github.asmgf.reactnativedetector.detector;

import org.opencv.core.Mat;

public class Detector {
    static {
        System.loadLibrary("c++_shared");
        System.loadLibrary("opencv_java3");
        System.loadLibrary("detector");
    }

    public boolean detect(Mat rgbaMat) {
        return detect(rgbaMat.getNativeObjAddr());
    }

    private native boolean detect(long rgbaMatAddr);
}
