package com.github.asmgf.reactnativedetector.detector;

import org.junit.Test;
import org.opencv.core.Mat;

import static org.junit.Assert.assertFalse;

public class DetectorTest {
    @Test
    public void test_emptyMat_notDetected() {
        Detector detector = new Detector();
        Mat emptyMat = new Mat();

        boolean detected = detector.detect(emptyMat);

        assertFalse(detected);
    }
}
