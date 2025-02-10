package com.example.androidgraphicsintro.TwoDimViews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.androidgraphicsintro.Graphics.MatrixTwoDim
import com.example.androidgraphicsintro.Graphics.TwoDimAffineTransformations

class PlottingGraphsView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {

    val plotValues: List<Int> = listOf( 11,29,10,20,12,5,31,24,21,13)
    var screenHeight: Int = 0
    var screenWidth: Int = 0

    init {
        // no need to call super() since that is done internally by kotlin as seen in https://tinyurl.com/2s5z4ttr
        this.screenHeight = resources.displayMetrics.heightPixels
        this.screenWidth = resources.displayMetrics.widthPixels
    }

    fun plotGraph(points: List<Int>, width: Int, height: Int, canvas: Canvas?) {
        val pointCords = emptyList<MutableList<Float>>().toMutableList()
        val minPoint = points.min()
        val maxPoint = points.max()
        for (i in points.indices){
            pointCords.add(i, listOf(i.toFloat(), points[i].toFloat(), 1.0F).toMutableList() )
        }
        val pointsMatRaw = MatrixTwoDim(points.size, 3, pointCords)
        val pointsMatHomogeneus = pointsMatRaw.transpose()

        val yscale = height.toFloat()/(maxPoint.toFloat() - minPoint.toFloat())
        val xscale = width.toFloat()/points.size.toFloat()

        var plotCords = TwoDimAffineTransformations.translate(pointsMatHomogeneus, 0, -minPoint)
        plotCords = TwoDimAffineTransformations.scale(plotCords, xscale, yscale)

        val plotPaint = Paint()
        plotPaint.color = 0xffff0000.toInt()
        plotPaint.style = Paint.Style.STROKE
        plotPaint.strokeWidth = 5F
        TwoDimShapeRenderer.drawSegmentedLine(plotCords, canvas, plotPaint)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        plotGraph(
            this.plotValues,
            resources.displayMetrics.widthPixels,
            resources.displayMetrics.heightPixels,
            canvas
        )
    }

}