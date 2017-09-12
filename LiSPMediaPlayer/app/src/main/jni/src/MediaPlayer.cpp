#include <jni.h>
#include "include/logger.h"
#include <string.h>

#ifdef DEGUB_MEM
#include "include/mymalloc.h"
#else
#include <malloc.h>
#endif

#ifdef __cplusplus
extern int sender(int argc, char *argv[]);
extern int receiver(int argc, char *argv[]);
extern "C"
{
#endif

    class A
    {
    public:
        A()
        {
            LOG_INFO("constructor of A");
        }
        ~A()
        {
            LOG_INFO("unconstrctor of A");
        }
    };

/*
 * Class:     com_ericsson_lispmediaplayer_MainActivity
 * Method:    test
 * Signature: ()V
 */
//#define MY_LOG_LEVEL MY_LOG_LEVEL_DEBUG
JNIEXPORT void JNICALL Java_com_ericsson_lispmediaplayer_MainActivity_test
(JNIEnv *env, jobject obj)
{
    LOG_INFO("Hello, My test");
    char * p = (char *) malloc(5);
    free(p);

    int * p1 = new int(10);
    delete p1;

    int * p2 = new int[10];
    delete[] p2;

    A * a = new A[2];
    delete[] a;

}

/*
 * Class:     com_ericsson_lispmediaplayer_MainActivity
 * Method:    send
 * Signature: (I[Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_ericsson_lispmediaplayer_MainActivity_send
  (JNIEnv *env, jobject object, jobjectArray jargv) {
    //char ** argv = (char **)jargv;
    int stringCount = env->GetArrayLength(jargv);
    char ** myargs = (char **)malloc(sizeof(char *) * stringCount);
    memset(myargs, 0, sizeof(char *) * stringCount);
    for (int i=0; i<stringCount; i++) {
        jstring string = (jstring) (env->GetObjectArrayElement(jargv, i));
        myargs[i] = (char *)malloc(sizeof(char) * (env->GetStringUTFLength(string) +1 ));
        memset(myargs[i], 0, sizeof(char *) * (env->GetStringUTFLength(string) +1));
        const char *rawString = env->GetStringUTFChars(string, 0);
        memcpy(myargs[i], rawString, sizeof(char) * (env->GetStringUTFLength(string)));
        env->ReleaseStringUTFChars(string, rawString);
        env->DeleteLocalRef(string);
    }
    sender(stringCount, myargs);
    LOG_INFO("Java_com_ericsson_lispmediaplayer_MainActivity_send sender finished");
    for(int i=0; i<stringCount; i++) {
        LOG_INFO("myargs[%d] = %p", i, myargs[i]);
        //free(myargs[i]);  //why can not free()
    }
    LOG_INFO("myargs = %p", myargs);
    free(myargs);
}

/*
 * Class:     com_ericsson_lispmediaplayer_MainActivity
 * Method:    receive
 * Signature: (I[Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_com_ericsson_lispmediaplayer_MainActivity_receive
  (JNIEnv * env, jobject object, jobjectArray jargv) {
    int stringCount = env->GetArrayLength(jargv);
        char ** myargs = (char **)malloc(sizeof(char *) * stringCount);
        memset(myargs, 0, sizeof(char *) * stringCount);
        for (int i=0; i<stringCount; i++) {
            jstring string = (jstring) (env->GetObjectArrayElement(jargv, i));
            myargs[i] = (char *)malloc(sizeof(char) * (env->GetStringUTFLength(string) +1));
            memset(myargs[i], 0, sizeof(char *) * (env->GetStringUTFLength(string) +1));
            const char *rawString = env->GetStringUTFChars(string, 0);
            memcpy(myargs[i], rawString, sizeof(char) * (env->GetStringUTFLength(string)));
            env->ReleaseStringUTFChars(string, rawString);
            env->DeleteLocalRef(string);
        }
        receiver(stringCount, myargs);
        for(int i=0; i<stringCount; i++) {
            free(myargs[i]);
        }
        free(myargs);
}

#ifdef __cplusplus
}
#endif
