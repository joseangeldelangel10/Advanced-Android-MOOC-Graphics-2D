package com.example.androidgraphicsintro.ThreeDimViews

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import com.example.androidgraphicsintro.Graphics.MatrixTwoDim

object ThreeDimShapeRenderer {

    /**
     * function that takes a 4x8 matrix representing the vertices of
     * a cube in 3D homogeneous coordinates and in the order represented
     * in AndroidGraphicsIntro/app/src/main/res/drawable/index_reference_for_cube_drawing.jpg
     */
    fun drawCube(vertices: MatrixTwoDim, paint: Paint, canvas: Canvas?){
        if (vertices.height != 4){
            throw Exception(
                "Invalid vertices format, please pass in a matrix " +
                "where mat.height equals 4 (is in 3d homogeneous coordinates)"
            )
        }
        if (vertices.width != 8) {
            throw Exception(
                "Trying to draw a cube with an invalid number of vertices, a " +
                "cube should have 8 vertices for it to be drawn"
            )
        }
        val  horizontalVertices = vertices.transpose()
        // line for v0 to v1
        /**
         * [ x , y, x]
         * [ 00, 01, 02]   v0
         * [ 10, 11, 12]   v1
         * [ 20, 21, 22]   v2
         * [ 30, 31, 32]   v3
         * [ 40, 41, 42]   v4
         * [ 50, 51, 52]   v5
         * [ 60, 61, 62]   v6
         * [ 70, 71, 72]   v7
         */
        canvas?.drawLine(
            horizontalVertices.values[0][0],
            horizontalVertices.values[0][1],
            horizontalVertices.values[1][0],
            horizontalVertices.values[1][1],
            paint)
        // line for v0 to v2
        canvas?.drawLine(
            horizontalVertices.values[0][0],
            horizontalVertices.values[0][1],
            horizontalVertices.values[2][0],
            horizontalVertices.values[2][1],
            paint)
        // line for v0 to v3
        canvas?.drawLine(
            horizontalVertices.values[0][0],
            horizontalVertices.values[0][1],
            horizontalVertices.values[3][0],
            horizontalVertices.values[3][1],
            paint)
        // line for v1 to v4
        canvas?.drawLine(
            horizontalVertices.values[1][0],
            horizontalVertices.values[1][1],
            horizontalVertices.values[4][0],
            horizontalVertices.values[4][1],
            paint)
        // line for v1 to v5
        canvas?.drawLine(
            horizontalVertices.values[1][0],
            horizontalVertices.values[1][1],
            horizontalVertices.values[5][0],
            horizontalVertices.values[5][1],
            paint)
        // line for v2 to v4
        canvas?.drawLine(
            horizontalVertices.values[2][0],
            horizontalVertices.values[2][1],
            horizontalVertices.values[4][0],
            horizontalVertices.values[4][1],
            paint)
        // line for v2 to v6
        canvas?.drawLine(
            horizontalVertices.values[2][0],
            horizontalVertices.values[2][1],
            horizontalVertices.values[6][0],
            horizontalVertices.values[6][1],
            paint)
        // line for v3 to v5
        canvas?.drawLine(
            horizontalVertices.values[3][0],
            horizontalVertices.values[3][1],
            horizontalVertices.values[5][0],
            horizontalVertices.values[5][1],
            paint)
        // line for v3 to v6
        canvas?.drawLine(
            horizontalVertices.values[3][0],
            horizontalVertices.values[3][1],
            horizontalVertices.values[6][0],
            horizontalVertices.values[6][1],
            paint)
        // line for v4 to v7
        canvas?.drawLine(
            horizontalVertices.values[4][0],
            horizontalVertices.values[4][1],
            horizontalVertices.values[7][0],
            horizontalVertices.values[7][1],
            paint)
        // line for v5 to v7
        canvas?.drawLine(
            horizontalVertices.values[5][0],
            horizontalVertices.values[5][1],
            horizontalVertices.values[7][0],
            horizontalVertices.values[7][1],
            paint)
        // line for v6 to v7
        canvas?.drawLine(
            horizontalVertices.values[6][0],
            horizontalVertices.values[6][1],
            horizontalVertices.values[7][0],
            horizontalVertices.values[7][1],
            paint)

        // will fill the front of the cube
        val path = Path()
        path.moveTo(horizontalVertices.values[3][0], horizontalVertices.values[3][1])
        path.lineTo(horizontalVertices.values[5][0], horizontalVertices.values[5][1])
        path.lineTo(horizontalVertices.values[7][0], horizontalVertices.values[7][1])
        path.lineTo(horizontalVertices.values[6][0], horizontalVertices.values[6][1])
        path.close()
        val frontFacePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        frontFacePaint.style = Paint.Style.FILL
        frontFacePaint.color = 0x7D000000.toInt()
        frontFacePaint.strokeWidth = 5F
        canvas?.drawPath(path, frontFacePaint)
    }
}