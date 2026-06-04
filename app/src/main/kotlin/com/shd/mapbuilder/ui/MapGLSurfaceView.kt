package com.shd.mapbuilder.ui

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.shd.mapbuilder.jni.MapEngineJNI

class MapGLSurfaceView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
    private var renderThread: Thread? = null
    private var isRunning = false
    
    private val scaleDetector = ScaleGestureDetector(context, object: ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            try { MapEngineJNI.nativeZoomCamera(detector.scaleFactor) } catch (e:Exception) {}
            return true
        }
    })
    
    private val gestureDetector = GestureDetector(context, object: GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(e1: MotionEvent?, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            try { MapEngineJNI.nativeOrbitCamera(-distanceX * 0.01f, -distanceY * 0.01f) } catch(e:Exception) {}
            return true
        }
        override fun onSingleTapUp(e: MotionEvent): Boolean {
            try { MapEngineJNI.nativeTouchPick(e.x, e.y) } catch(e:Exception){}
            return true
        }
    })

    init { holder.addCallback(this) }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        scaleDetector.onTouchEvent(event)
        gestureDetector.onTouchEvent(event)
        return true
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        try { MapEngineJNI.nativeInit(holder.surface, width, height) } catch(e:Exception) {}
        isRunning = true
        renderThread = Thread {
            while (isRunning) {
                try { MapEngineJNI.nativeDrawFrame() } catch(e:Exception) {}
                Thread.sleep(16)
            }
        }.apply { start() }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, w: Int, h: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        isRunning = false
        renderThread?.join()
        try { MapEngineJNI.nativeDestroy() } catch(e:Exception) {}
    }
}
