package com.example.androidgraphicsintro.ThreeDimViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.androidgraphicsintro.Graphics.ThreeDimAffineTransformations
import com.example.androidgraphicsintro.Graphics.ThreeDimShapeFactory

class DrawingCubeView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {
    var redPaint: Paint
    var bluePaint: Paint
    var myPaint: Paint

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

        myPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        myPaint.style = Paint.Style.STROKE
        myPaint.color = 0xff00ff00.toInt()
        myPaint.strokeWidth = 5F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        var cubeVertices = ThreeDimShapeFactory.getCube(arrayListOf(1.0F, 1.0F, 1.0F), 40.0F)
        cubeVertices = ThreeDimAffineTransformations.scale(cubeVertices, 3F, 3F, 3F)
        ThreeDimShapeRenderer.drawCube(cubeVertices, redPaint, canvas)

        var rotatedCube = ThreeDimAffineTransformations.translate(cubeVertices, 240, 80, 0)
        rotatedCube = ThreeDimAffineTransformations.rotateZ(rotatedCube, 100)
        rotatedCube = ThreeDimAffineTransformations.rotateY(rotatedCube, 30)
        rotatedCube = ThreeDimAffineTransformations.translate(rotatedCube, 240, 0,0)
        ThreeDimShapeRenderer.drawCube(rotatedCube, bluePaint, canvas)
    }
}