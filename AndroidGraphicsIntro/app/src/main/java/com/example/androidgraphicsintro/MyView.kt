package com.example.androidgraphicsintro

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {

    var redPaint: Paint

    init {
        // no need to call super() since that is done internally by kotlin as seen in https://tinyurl.com/2s5z4ttr
        redPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        redPaint.style = Paint.Style.STROKE
        redPaint.color = 0xffff0000.toInt()
        redPaint.strokeWidth = 5F
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        // add drawing code here
        canvas?.drawRect(10F, 30F, 200F, 200F, this.redPaint)
    }

}