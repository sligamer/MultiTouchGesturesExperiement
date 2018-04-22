package com.lab79.sligamer.multitouchgesturesexperiement

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

/**
 * Created by Justin Freres on 4/10/2018.
 * Lab 7-9 Multi Touch Gesture Experiment
 * Plugin Support with kotlin_version = '1.2.40'
 */

class GameView: View {

    // DECLARE VARIABLES
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var squareOne: SquareOne = SquareOne()
    private var squareTwo: SquareTwo = SquareTwo()

    constructor(context: Context?) : super(context) {

        // CREATE TWO SQUARES AND SET THE PAINT
        // SET THE RADIUS X, Y, LOCATIONS

        paint.color = Color.rgb(126, 79, 43)

        // SET THE SQUARE ONE UP
        squareOne.setPaint(paint)

        squareOne.setRadius(100F)
        squareOne.setX(400F)
        squareOne.setY(300F)

        // SET TEH SQUARE TWO UP
        squareTwo.setPaint(paint)

        squareTwo.setRadius(75F)
        squareTwo.setX(400F)
        squareTwo.setY(300F)
    }

    override fun onDraw(canvas: Canvas?) {
        // TASK 1: FILL THE BACKGROUND OF THE CANVAS
        canvas!!.drawRGB(248,232,198)

        // TASK 2: DRAW THE SQUARE
        canvas!!.drawCircle(squareOne.getX(), squareOne.getY(),squareOne.getRadius(), squareOne.getPaint())

        canvas!!.drawCircle(squareTwo.getX(), squareTwo.getY(),squareTwo.getRadius(), squareTwo.getPaint())
    }

    fun update() {
        invalidate()
    }


     // INIT THE POINTERS USED IN TEH MULTI TOUCH GESTURES
    companion object {
        const val IsRemoved = -1
    }

    private var primaryPointer: Int = IsRemoved
    private var primaryPointerIndex: Int = IsRemoved
    private var secondaryPointer: Int = IsRemoved
    private var secondaryPointerIndex: Int = IsRemoved

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        // TASK 1: IDENTIFY THE TOUCH ACTION BEING PERFORMED
        val touchAction = event!!.actionMasked

        // TASK 2: RESPOND TO TWO POSSIBLE TOUCH EVENTS
        when(touchAction)
        {
            MotionEvent.ACTION_DOWN -> {
                // PRIMARY POINTER IS REGISTERED ON THE SCREEN
                primaryPointerIndex = event.actionIndex
                primaryPointer = event.getPointerId(primaryPointerIndex)
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                // PRIMARY POINTER IS REGISTERED ON THE SCREEN
                secondaryPointerIndex = event.actionIndex
                secondaryPointer = event.getPointerId(secondaryPointerIndex)
            }
            MotionEvent.ACTION_UP -> {
                primaryPointerIndex = IsRemoved
                secondaryPointerIndex = IsRemoved

            }
            MotionEvent.ACTION_POINTER_UP -> {
                primaryPointerIndex = IsRemoved
                secondaryPointerIndex = IsRemoved

            }

            MotionEvent.ACTION_MOVE -> {

                when {
                    primaryPointerIndex != IsRemoved -> {
                        squareOne.setX(event.getX(primaryPointerIndex))
                        squareOne.setY(event.getY(primaryPointerIndex))
                    }
                }
                when {
                    secondaryPointerIndex != IsRemoved -> {
                        squareTwo.setX(event.getX(secondaryPointerIndex))
                        squareTwo.setY(event.getY(secondaryPointerIndex))
                    }
                }
            }
        }

        // TASK 3: INVALIDATE THE VIEW
        invalidate()

        // TASK 4: RETURN TRUE
        return true

    }

}