LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE := NdkTest//moduleName
LOCAL_SRC_FILES := NdkTest.cpp//上面创建的NdkTest.cpp
include $(BUILD_SHARED_LIBRARY)