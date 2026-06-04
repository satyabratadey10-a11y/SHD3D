#include <jni.h>
#include <vector>
#include <string>

// Global mock state for the stubs to prevent crashes
static float currentFps = 60.0f;
static int currentDrawCalls = 12;
static float currentVram = 18.5f;
static float bakeProgressVal = -1.0f;

extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeInit(JNIEnv* env, jobject, jobject surface, jint w, jint h) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeResume(JNIEnv*, jobject) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativePause(JNIEnv*, jobject) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeDestroy(JNIEnv*, jobject) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeDrawFrame(JNIEnv*, jobject) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeOrbitCamera(JNIEnv*, jobject, jfloat dYaw, jfloat dPitch) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativePanCamera(JNIEnv*, jobject, jfloat dx, jfloat dy) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeZoomCamera(JNIEnv*, jobject, jfloat delta) {}
extern "C" JNIEXPORT jintArray JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeTouchPick(JNIEnv* env, jobject, jfloat x, jfloat y) {
    jintArray result = env->NewIntArray(3);
    jint arr[3] = {-1, -1, -1};
    env->SetIntArrayRegion(result, 0, 3, arr);
    return result;
}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativePlaceTile(JNIEnv*, jobject, jint x, jint y, jint z, jint typeId, jint matId) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeDeleteTile(JNIEnv*, jobject, jint x, jint y, jint z) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativePaintTile(JNIEnv*, jobject, jint x, jint y, jint z, jint matId) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeFillRegion(JNIEnv*, jobject, jint x1, jint y1, jint z, jint x2, jint y2, jint typeId, jint matId) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeLoadTilesBatch(JNIEnv* env, jobject, jstring json) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeClearMap(JNIEnv*, jobject) {}
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeStartLightmapBake(JNIEnv*, jobject, jint res, jint samples) { bakeProgressVal = 0.0f; }
extern "C" JNIEXPORT jfloat JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeBakeProgress(JNIEnv*, jobject) { return bakeProgressVal; }
extern "C" JNIEXPORT jboolean JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeBakeComplete(JNIEnv*, jobject) { return bakeProgressVal >= 1.0f; }
extern "C" JNIEXPORT jint JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeLoadTexture(JNIEnv* env, jobject, jstring path) { return 0; }
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeSetSkybox(JNIEnv* env, jobject, jstring path) {}
extern "C" JNIEXPORT jstring JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeSaveMap(JNIEnv* env, jobject) { return env->NewStringUTF("{}"); }
extern "C" JNIEXPORT jstring JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeExportOBJ(JNIEnv* env, jobject) { return env->NewStringUTF(""); }
extern "C" JNIEXPORT void JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeExportGLTF(JNIEnv* env, jobject, jstring path) {}
extern "C" JNIEXPORT jint JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeGetDrawCalls(JNIEnv*, jobject) { return currentDrawCalls; }
extern "C" JNIEXPORT jfloat JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeGetFPS(JNIEnv*, jobject) { return currentFps; }
extern "C" JNIEXPORT jfloat JNICALL Java_com_shd_mapbuilder_jni_MapEngineJNI_nativeGetVramMB(JNIEnv*, jobject) { return currentVram; }
