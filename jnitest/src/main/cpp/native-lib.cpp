//
// Created by LiKai on 2018-06-29.
//
#include <jni.h>
#include <string>

#ifdef __cplusplus
extern "C" {
#endif

jstring
Java_com_verifone_kail1_jnitest_MainActivity_stringFromJNI(JNIEnv *env, jobject jobj) {
    std::string hello = "hello world";
    char str[] = "Hello world";
    return env->NewStringUTF(str);
}

#ifdef __cplusplus
}
#endif
