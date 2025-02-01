package com.example.androidgraphicsintro

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class MyView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {

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
        myPaint.color = 0xff000000.toInt()
        myPaint.strokeWidth = 5F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // add drawing code here
        canvas?.drawRect(10F, 30F, 200F, 200F, this.redPaint)
        canvas?.drawCircle(300F, 300F, 250F, this.bluePaint)
        // canvas?.drawCircle(500F, 450F, 50F, this.redPaint)
        val lineCords = arrayListOf<ArrayList<Int>>(
            arrayListOf(50,300),
            arrayListOf(160,280),
            arrayListOf(300,380),
            arrayListOf(380,370),
            arrayListOf(280,450),
            arrayListOf(100,390),
            arrayListOf(160,380),
            arrayListOf(50,300)
        )
        var centroidX = 0F
        var centroidY = 0F

        val path = Path()
        path.moveTo(lineCords[0][0].toFloat(), lineCords[0][1].toFloat())
        centroidX += lineCords[0][0].toFloat()
        centroidY += lineCords[0][1].toFloat()
        for ( i in 1 until  lineCords.size ) {
            path.lineTo(lineCords[i][0].toFloat(), lineCords[i][1].toFloat())
            centroidX += lineCords[i][0].toFloat()
            centroidY += lineCords[i][1].toFloat()
        }
        canvas?.drawPath(path, this.myPaint)

        centroidX = centroidX/lineCords.size.toFloat()
        centroidY = centroidY/lineCords.size.toFloat()
        canvas?.drawCircle(centroidX, centroidY, 250F, this.myPaint)
    }

}