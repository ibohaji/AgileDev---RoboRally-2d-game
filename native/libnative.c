#include "Main_JNIHelper.h"
#include <stdio.h>

JNIEXPORT jobject JNICALL Java_Main_JNIHelper_JNITransaction
  (JNIEnv *env, jobject obj, jstring str) {
	jclass _cls = (*env)->FindClass(env, "Main/Main");
	jobject newObj = (*env)->AllocObject(env, _cls);
	jclass cls_ = (*env)->GetObjectClass(env, newObj);
	jfieldID fidJNIString = (*env)->GetFieldID(env, cls_, "JNIString", "Ljava/lang/String;");
	if (NULL == fidJNIString) return obj;
 
	(*env)->SetObjectField(env, newObj, fidJNIString, str);
	
	return newObj;
}
