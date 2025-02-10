package com.example.androidgraphicsintro

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import com.example.androidgraphicsintro.Graphics.MathUtils
import com.example.androidgraphicsintro.Graphics.MatrixTwoDim

class MyView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {

    var redPaint: Paint
    var bluePaint: Paint
    var myPaint: Paint
    var polygon: MutableList<MutableList<Float>>

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

        polygon = listOf<List<Float>>(
            listOf(500F, 300F, 1F),
            listOf(500F, 400F, 1F),
            listOf(600F, 400F, 1F),
            listOf(600F, 300F, 1F),
        ) as MutableList<MutableList<Float>>
    }

    fun drawPolygon(vertices: MatrixTwoDim, canvas: Canvas?, paint: Paint){
        if (vertices.height != 3){
            // consider matrix to be drawn can only be an homogeneus mat
            // with indexes:
            //      i=0 corresponding to x0_j
            //      i=1 corresponding to y1_j
            //      i=2 corresponding to 1_j
            throw Exception("invalid vertices, please pass in a homogeneus mat")
        }
        val path = Path()
        path.reset()
        path.moveTo(vertices.values[0][0], vertices.values[1][0])
        for (i in 1 until polygon.size){
            path.lineTo(vertices.values[0][i], vertices.values[1][i])
        }
        path.close()
        canvas?.drawPath(path, paint)
    }

    fun affineTransformation(matA: MatrixTwoDim, matB: MatrixTwoDim): MatrixTwoDim {
        return matA.multMat(matB)
    }

    fun translate(mat: MatrixTwoDim, deltaX: Int, deltaY: Int): MatrixTwoDim {
        val rawTranslationMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(1F, 0F, deltaX.toFloat()),
            listOf(0F, 1F, deltaY.toFloat()),
            listOf(0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val translationMat = MatrixTwoDim(3, 3, rawTranslationMat)
        return affineTransformation(translationMat, mat)
    }

    fun rotate(mat: MatrixTwoDim, angleDeg: Int): MatrixTwoDim {
        val angleRad = MathUtils.degToRad(angleDeg.toFloat())
        val cosTheta = Math.cos(angleRad).toFloat()
        val sinTheta = Math.sin(angleRad).toFloat()
        val rawRotationMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(cosTheta, -sinTheta, 0F),
            listOf(sinTheta, cosTheta, 0F),
            listOf(0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val rotationMat = MatrixTwoDim(3, 3, rawRotationMat)
        return affineTransformation(rotationMat, mat)
    }

    fun scale(mat: MatrixTwoDim, xScaling: Float, yScaling: Float): MatrixTwoDim {
        val rawScalingMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(xScaling, 0F, 0F),
            listOf(0F, yScaling, 0F),
            listOf(0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val scalingMat = MatrixTwoDim(3, 3, rawScalingMat)
        return affineTransformation(scalingMat, mat)
    }

    fun shear(mat: MatrixTwoDim, xFactor: Float, yFactor: Float): MatrixTwoDim {
        val rawShearingMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(1F, xFactor, 0F),
            listOf(yFactor, 1F, 0F),
            listOf(0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val shearingMat = MatrixTwoDim(3, 3, rawShearingMat)
        return affineTransformation(shearingMat, mat)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val linear = LinearGradient(500F, 400F, 600F, 300F, Color.BLUE, Color.RED, Shader.TileMode.MIRROR)
        val gradientPaint = Paint()
        gradientPaint.style = Paint.Style.FILL
        gradientPaint.shader = linear

        val polygonMat = MatrixTwoDim(4, 3, polygon)
        val polygonCentroid = polygonMat.flatten()
        val homogeneusPolygonMat = polygonMat.transpose()
        val polygonMinusCenter = translate(homogeneusPolygonMat, -polygonCentroid[0].toInt(), -polygonCentroid[1].toInt())
        val polygonRotated = rotate(polygonMinusCenter, 45)
        val polygonRotatedBackInCenter = translate(polygonRotated, polygonCentroid[0].toInt(), polygonCentroid[1].toInt())

        drawPolygon(polygonRotatedBackInCenter, canvas, gradientPaint)
    }

}