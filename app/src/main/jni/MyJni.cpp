#include "com_yun_software_yunlearn_jni_MyJni.h"
jint JNICALL Java_com_yun_software_yunlearn_jni_MyJni_testAdd
  (JNIEnv *, jobject, jint a, jint b){

       return a+b;

  }
void JNICALL Java_com_yun_software_yunlearn_jni_MyJni_getHelloWord
  (JNIEnv *env, jobject, jstring){


   env->NewStringUTF("hello from JNI !");


  }

