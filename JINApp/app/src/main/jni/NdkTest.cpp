#include "com_mvp_jingshuai_jinapp_NdkTest.h"

JNIEXPORT jstring JNICALL Java_com_mvp_jingshuai_jinapp_NdkTest_getString
        (JNIEnv *env, jclass type) {//具体实现

    return env->NewStringUTF("hello world!!!");
}

/*
 * Class:     com_jeanboy_demo_jnitest_NdkTest
 * Method:    doAdd
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_com_mvp_jingshuai_jinapp_NdkTest_doAdd
        (JNIEnv *env, jclass type, jint param1, jint param2) {//具体实现

    return param1 + param1;
}