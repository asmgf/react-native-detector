#include <Detector.hpp>

#include <jni.h>

extern "C" JNIEXPORT jboolean JNICALL
Java_com_github_asmgf_reactnativedetector_detector_Detector_detect(
        JNIEnv* env, jobject object, jlong rgbaMatAddr) {
    cv::Mat* rgbaMat = reinterpret_cast<cv::Mat*>(rgbaMatAddr);
    static asmgf::detector::Detector detector;
    return detector.detect(*rgbaMat);
}
