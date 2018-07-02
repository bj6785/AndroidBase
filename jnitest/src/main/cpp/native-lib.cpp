//
// Created by LiKai on 2018-06-29.
//
#include <jni.h>
#include <string>
#include "debug.h"

#ifdef __cplusplus
extern "C" {
#endif

#define LOG_TAG "Native-lib"

// 包名+类名+方法名(JNIEnv *env, jobject jobj)
jstring
Java_com_verifone_kail1_jnitest_MainActivity_stringFromJNI(JNIEnv *env, jobject jobj) {
    std::string hello = "hello world";
    char str[] = "Hello world";
    return env->NewStringUTF(str);
}

void
Java_com_verifone_kail1_jnitest_MainActivity_stringToJNI(JNIEnv *env, jobject jobj, jstring jstr) {
    const char *nativeString = env->GetStringUTFChars(jstr, false);
    LOGD("%s\n", nativeString);
    env->ReleaseStringUTFChars(jstr, nativeString);
}


#ifdef __cplusplus
}
#endif
