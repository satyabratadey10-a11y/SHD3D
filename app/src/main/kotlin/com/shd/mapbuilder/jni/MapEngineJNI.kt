package com.shd.mapbuilder.jni

import android.view.Surface

object MapEngineJNI {
    init {
        try {
            System.loadLibrary("mapengine")
        } catch (e: UnsatisfiedLinkError) {
            e.printStackTrace()
        }
    }
    
    external fun nativeInit(surface: Surface, w: Int, h: Int)
    external fun nativeResume()
    external fun nativePause()
    external fun nativeDestroy()
    external fun nativeDrawFrame()
    external fun nativeOrbitCamera(dYaw: Float, dPitch: Float)
    external fun nativePanCamera(dx: Float, dy: Float)
    external fun nativeZoomCamera(delta: Float)
    external fun nativeTouchPick(x: Float, y: Float): IntArray
    external fun nativePlaceTile(x: Int, y: Int, z: Int, typeId: Int, matId: Int)
    external fun nativeDeleteTile(x: Int, y: Int, z: Int)
    external fun nativePaintTile(x: Int, y: Int, z: Int, matId: Int)
    external fun nativeFillRegion(x1: Int, y1: Int, z: Int, x2: Int, y2: Int, typeId: Int, matId: Int)
    external fun nativeLoadTilesBatch(tilesJson: String)
    external fun nativeClearMap()
    external fun nativeStartLightmapBake(resolution: Int, samples: Int)
    external fun nativeBakeProgress(): Float
    external fun nativeBakeComplete(): Boolean
    external fun nativeLoadTexture(assetPath: String): Int
    external fun nativeSetSkybox(assetPath: String)
    external fun nativeSaveMap(): String
    external fun nativeExportOBJ(): String
    external fun nativeExportGLTF(outputPath: String)
    external fun nativeGetDrawCalls(): Int
    external fun nativeGetFPS(): Float
    external fun nativeGetVramMB(): Float
}
