package com.example.androidgraphicsintro.Graphics

import kotlin.math.cos

object ThreeDimAffineTransformations {
    fun affineTransformation(matA: MatrixTwoDim, matB: MatrixTwoDim): MatrixTwoDim {
        var rawMult = matA.multMat(matB)
        // normalize
        val wValues = rawMult.values[3]
        for (i in 0 until rawMult.height) {
            for (j in 0 until rawMult.width){
                if (i == 3){
                    rawMult.values[i][j] = 1F // set W as F
                }
                else {
                    if (wValues[j] != 0F) {
                        rawMult.values[i][j] = (rawMult.values[i][j]) / (wValues[j])
                    }
                }
            }
        }
        return rawMult
    }

    fun validateMat(mat: MatrixTwoDim){
        if (mat.height != 4){
            throw Exception("Invalid input matrix passed, input matrix should have a size 4xN")
        }
    }

    fun translate(mat: MatrixTwoDim, deltaX: Int, deltaY: Int, deltaZ: Int): MatrixTwoDim {
        validateMat(mat)
        val rawTranslationMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(1F, 0F, 0F, deltaX.toFloat()),
            listOf(0F, 1F, 0F, deltaY.toFloat()),
            listOf(0F, 0F, 1F, deltaZ.toFloat()),
            listOf(0F, 0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val translationMat = MatrixTwoDim(4, 4, rawTranslationMat)
        return affineTransformation(translationMat, mat)
    }

    fun rotateX(mat: MatrixTwoDim, angleDeg: Int): MatrixTwoDim {
        validateMat(mat)
        val angleRad = MathUtils.degToRad(angleDeg.toFloat())
        val cosTheta = Math.cos(angleRad).toFloat()
        val sinTheta = Math.sin(angleRad).toFloat()
        val rawRotationMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(1F, 0F, 0F, 0F),
            listOf(0F, cosTheta, -sinTheta, 0F),
            listOf(0F, sinTheta, cosTheta, 0F),
            listOf(0F, 0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val rotationMat = MatrixTwoDim(4, 4, rawRotationMat)
        return affineTransformation(rotationMat, mat)
    }

    fun rotateY(mat: MatrixTwoDim, angleDeg: Int): MatrixTwoDim {
        validateMat(mat)
        val angleRad = MathUtils.degToRad(angleDeg.toFloat())
        val cosTheta = Math.cos(angleRad).toFloat()
        val sinTheta = Math.sin(angleRad).toFloat()
        val rawRotationMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(cosTheta, 0F, sinTheta, 0F),
            listOf(0F, 1F, 0F, 0F),
            listOf(-sinTheta, 0F, cosTheta, 0F),
            listOf(0F, 0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val rotationMat = MatrixTwoDim(4, 4, rawRotationMat)
        return affineTransformation(rotationMat, mat)
    }

    fun rotateZ(mat: MatrixTwoDim, angleDeg: Int): MatrixTwoDim {
        validateMat(mat)
        val angleRad = MathUtils.degToRad(angleDeg.toFloat())
        val cosTheta = Math.cos(angleRad).toFloat()
        val sinTheta = Math.sin(angleRad).toFloat()
        val rawRotationMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(cosTheta, -sinTheta, 0F, 0F),
            listOf(sinTheta, cosTheta, 0F, 0F),
            listOf(0F, 0F, 1F, 0F),
            listOf(0F, 0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val rotationMat = MatrixTwoDim(4, 4, rawRotationMat)
        return affineTransformation(rotationMat, mat)
    }

    fun scale(mat: MatrixTwoDim, xScaling: Float, yScaling: Float, zScaling: Float): MatrixTwoDim {
        validateMat(mat)
        val rawScalingMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(xScaling, 0F, 0F, 0F),
            listOf(0F, yScaling, 0F, 0F),
            listOf(0F, 0F, zScaling, 0F),
            listOf(0F, 0F, 0F, 1F),
        ) as MutableList<MutableList<Float>>
        val scalingMat = MatrixTwoDim(4, 4, rawScalingMat)
        return affineTransformation(scalingMat, mat)
    }
}