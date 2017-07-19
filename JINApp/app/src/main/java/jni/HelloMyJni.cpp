//
// Created by eqruvvz on 7/19/2017.
//
#include <jni.h>

#include com_example_hellomyjni_JniClient.h

JNIEXPORT jstring JNICALL Java_com_example_hellomyjni_JniClient_sayName
  (JNIEnv *env, jclass) {
    return env->NewStringUTF(I'm Spike!);
}
</jni.h>

