LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_JAVA_LIBRARIES := okhttp
#LOCAL_PREBUILT_JAVA_LIBRARIES := mimecraft-1.1.0$(COMMON_JAVA_PACKAGE_SUFFIX)
LOCAL_STATIC_JAVA_LIBRARIES := jsoup-1.7.2
LOCAL_PROGUARD_FLAG_FILES := proguard-rules.txt

LOCAL_PACKAGE_NAME := CaptivePortalLogin
LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)

include $(CLEAR_VARS)

#LOCAL_MODULE := optional1
LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := jsoup-1.7.2:lib/jsoup-1.7.2.jar
include $(BUILD_MULTI_PREBUILT)
