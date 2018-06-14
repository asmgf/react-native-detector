package com.github.asmgf.reactnativedetector.detector;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.test.InstrumentationRegistry;

import org.junit.Test;
import org.opencv.android.Utils;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DetectorTest {
    @Test
    public void test_emptyMat_notDetected() {
        Detector detector = new Detector();
        Mat emptyMat = new Mat(0, 0, CvType.CV_8UC4);

        boolean detected = detector.detect(emptyMat);

        assertFalse(detected);
    }

    @Test
    public void test_fullText_detected() throws Exception {
        Detector detector = new Detector();
        Mat mat = getMatFromAssets("text-full.jpg");

        boolean detected = detector.detect(mat);

        assertTrue(detected);
    }

    @Test
    public void test_croppedText_notDetected() throws Exception {
        Detector detector = new Detector();
        Mat mat = getMatFromAssets("text-cropped.jpeg");

        boolean detected = detector.detect(mat);

        assertFalse(detected);
    }

    private static Bitmap getBitmapFromAssets(String name) throws IOException {
        Context context = InstrumentationRegistry.getContext();
        try (InputStream stream = context.getAssets().open(name)) {
            return BitmapFactory.decodeStream(stream);
        }
    }

    private static Mat getMatFromAssets(String name) throws IOException {
        Mat mat = new Mat();
        Utils.bitmapToMat(getBitmapFromAssets(name), mat);
        return mat;
    }
}
