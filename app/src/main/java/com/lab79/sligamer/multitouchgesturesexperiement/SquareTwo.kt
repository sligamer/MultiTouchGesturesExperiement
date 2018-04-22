package com.lab79.sligamer.multitouchgesturesexperiement

import android.graphics.Paint

class SquareTwo {

    // DECLARE VARIABLES
    private lateinit var mPaint: Paint
    private var mRadius: Float = 0F
    private var mX: Float = 0F
    private var mY: Float = 0F

    fun setPaint(paint: Paint) {
        mPaint = paint
    }

    fun getPaint(): Paint {
        return mPaint
    }

    fun setRadius(radius: Float) {
        mRadius = radius
    }

    fun getRadius(): Float {
        return mRadius
    }

    fun setX(x: Float) {
        mX = x
    }
    fun getX(): Float {
        return mX
    }

    fun setY(y: Float) {
        mY = y
    }
    fun getY(): Float {
        return mY
    }
}