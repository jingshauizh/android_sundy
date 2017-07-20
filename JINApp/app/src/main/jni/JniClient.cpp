//
// Created by Next on 2016/8/12.
//

#include <jni.h>
#include "com_mvp_jingshuai_jinapp_JniClient.h"

JNIEXPORT jstring JNICALL Java_com_mvp_jingshuai_jinapp_JniClient_sayName
        (JNIEnv *env, jclass type) {
    return env->NewStringUTF("I'm Spike!");
}



