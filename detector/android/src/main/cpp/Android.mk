LOCAL_PATH := $(call my-dir)
PROJECT_PATH := $(LOCAL_PATH)/../../..
OPENCV_PATH := $(PROJECT_PATH)/libs/jni/opencv
NATIVE_PATH := $(PROJECT_PATH)/../native

include $(CLEAR_VARS)
LOCAL_MODULE := opencv
LOCAL_SRC_FILES += $(OPENCV_PATH)/$(TARGET_ARCH_ABI)/libopencv_java3.so
LOCAL_EXPORT_C_INCLUDES += $(OPENCV_PATH)/include
include $(PREBUILT_SHARED_LIBRARY)

include $(CLEAR_VARS)
LOCAL_MODULE := detector
LOCAL_CPP_FEATURES += rtti exceptions
LOCAL_C_INCLUDES += $(NATIVE_PATH)/include
LOCAL_SRC_FILES += $(NATIVE_PATH)/src/Detector.cpp \
    $(LOCAL_PATH)/com/github/asmgf/reactnativedetector/detector/Detector.cpp
LOCAL_SHARED_LIBRARIES += opencv
include $(BUILD_SHARED_LIBRARY)
