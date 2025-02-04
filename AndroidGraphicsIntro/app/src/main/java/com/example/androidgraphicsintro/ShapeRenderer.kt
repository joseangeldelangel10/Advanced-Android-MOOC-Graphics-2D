package com.example.androidgraphicsintro

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path

object ShapeRenderer {
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
        for (i in 1 until vertices.width){
            path.lineTo(vertices.values[0][i], vertices.values[1][i])
        }
        path.close()
        canvas?.drawPath(path, paint)
    }

    fun drawSegmentedLine(vertices: MatrixTwoDim, canvas: Canvas?, paint: Paint){
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
        for (i in 1 until vertices.width){
            path.lineTo(vertices.values[0][i], vertices.values[1][i])
        }
        canvas?.drawPath(path, paint)
    }
}