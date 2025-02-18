package com.example.androidgraphicsintro.ThreeDimViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.example.androidgraphicsintro.Graphics.ThreeDimAffineTransformations
import com.example.androidgraphicsintro.Graphics.ThreeDimShapeFactory
import java.util.Timer
import java.util.TimerTask
import kotlin.concurrent.timerTask
import android.util.Log
import com.example.androidgraphicsintro.Graphics.MatrixTwoDim

const val DISPLACEMENT_DELTA = 5

class LeftToRightCubeView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {
    var redPaint: Paint
    var bluePaint: Paint
    var timer: Timer
    val animateLeftToRight: TimerTask
    var x_pos: Int
    var multiplier: Int
    var cubeVertices: MatrixTwoDim

    init {
        // no need to call super() since that is done internally by kotlin as seen in https://tinyurl.com/2s5z4ttr
        redPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        redPaint.style = Paint.Style.STROKE
        redPaint.color = 0xffff0000.toInt()
        redPaint.strokeWidth = 5F

        bluePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        bluePaint.style = Paint.Style.STROKE
        bluePaint.color = 0xff0000ff.toInt()
        bluePaint.strokeWidth = 5F

        timer = Timer()
        x_pos = 0
        multiplier = 1
        cubeVertices = ThreeDimShapeFactory.getCube(arrayListOf(0.0F, 0.0F, 0.0F), 80.0F)
        cubeVertices = ThreeDimAffineTransformations.scale(cubeVertices, 3F, 3F, 3F)
        animateLeftToRight = TimerTaskProxy() {
            if (x_pos >= context.resources.displayMetrics.widthPixels ){
                multiplier = -1
            }
            else if (x_pos < 0.0 ){
                multiplier = 1
            }
            cubeVertices = ThreeDimAffineTransformations.translate(cubeVertices, multiplier* DISPLACEMENT_DELTA, 0, 0)
            x_pos += multiplier* DISPLACEMENT_DELTA
            this.invalidate()
        }
        timer.scheduleAtFixedRate(animateLeftToRight, 500, 50)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        ThreeDimShapeRenderer.drawCube(cubeVertices, redPaint, canvas)
    }
}


class TimerTaskProxy(func: () -> Unit): TimerTask() {
    val func: () -> Unit
    init {
        this.func = func
    }
    override fun run(){
        return this.func()
    }
}