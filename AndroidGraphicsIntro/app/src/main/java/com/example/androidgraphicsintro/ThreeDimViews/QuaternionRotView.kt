package com.example.androidgraphicsintro.ThreeDimViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.androidgraphicsintro.Graphics.MathUtils
import com.example.androidgraphicsintro.Graphics.MatrixTwoDim
import com.example.androidgraphicsintro.Graphics.ThreeDimAffineTransformations
import com.example.androidgraphicsintro.Graphics.ThreeDimShapeFactory
import java.util.Timer
import java.util.TimerTask

class QuaternionRotView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {
    var redPaint: Paint
    var bluePaint: Paint
    var timer: Timer
    val animateLeftToRight: TimerTask
    var angleDeg: Int
    var rotationAxis: MutableList<Float>
    var cubeVertices: MatrixTwoDim
    var renderedCubeVertices: MatrixTwoDim

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
        // x_pos = 0
        // multiplier = 1
        rotationAxis = mutableListOf(0F, 1F, 1F)
        angleDeg = 0
        cubeVertices = ThreeDimShapeFactory.getCube(arrayListOf(0.0F, 0.0F, 0.0F), 80.0F)
        cubeVertices = ThreeDimAffineTransformations.scale(cubeVertices, 3F, 3F, 3F)
        cubeVertices = ThreeDimAffineTransformations.translate(cubeVertices, 240, 240, 240)
        // cubeVertices = ThreeDimAffineTransformations.translate(cubeVertices, 240, 0, 0)
        renderedCubeVertices = cubeVertices
        animateLeftToRight = TimerTaskProxy() {
            val angleRad = MathUtils.degToRad(angleDeg.toFloat())
            val cosPhi = Math.cos(angleRad/2.0F)
            val sinPhi = Math.sin(angleRad/2.0F)
            val w = cosPhi
            val x = sinPhi*rotationAxis[0]
            val y = sinPhi*rotationAxis[1]
            val z = sinPhi*rotationAxis[2]
            renderedCubeVertices = ThreeDimAffineTransformations.rotateWithQuaternion(cubeVertices, w.toFloat(), x.toFloat(), y.toFloat(), z.toFloat())
            if (angleDeg >= 359){
                angleDeg = 0
            } else {
                angleDeg += 1
            }
            this.invalidate()
        }
        timer.scheduleAtFixedRate(animateLeftToRight, 500, 20)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        ThreeDimShapeRenderer.drawCube(renderedCubeVertices, redPaint, canvas)
    }
}