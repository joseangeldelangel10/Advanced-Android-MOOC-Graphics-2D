package com.example.androidgraphicsintro.Graphics

object ThreeDimShapeFactory {
    fun getCube(origin: ArrayList<Float>, size: Float): MatrixTwoDim {
        if (origin.size != 3) {
            throw Exception(
                "origin must be an arraylist of size 3," +
                " representing x, y, z origin coordinates"
            )
        }
        val originX = origin[0]
        val originY = origin[1]
        val originZ = origin[2]
        val v0 = mutableListOf<Float>(originX, originY, originZ, 1.0F)
        val v1 = mutableListOf<Float>(originX + size, originY, originZ, 1.0F)
        val v2 = mutableListOf<Float>(originX, originY + size, originZ, 1.0F)
        val v3 = mutableListOf<Float>(originX, originY, originZ + size, 1.0F)
        val v4 = mutableListOf<Float>(originX + size, originY + size, originZ, 1.0F)
        val v5 = mutableListOf<Float>(originX + size, originY, originZ + size, 1.0F)
        val v6 = mutableListOf<Float>(originX, originY + size, originZ + size, 1.0F)
        val v7 = mutableListOf<Float>(originX + size, originY + size, originZ + size, 1.0F)
        val vertices = mutableListOf<MutableList<Float>>(v0, v1, v2, v3, v4, v5, v6, v7)
        return MatrixTwoDim(8, 4, vertices).transpose()
    }
}