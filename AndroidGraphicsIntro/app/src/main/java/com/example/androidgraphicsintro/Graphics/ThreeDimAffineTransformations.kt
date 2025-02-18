package com.example.androidgraphicsintro.Graphics

import kotlin.math.cos

object ThreeDimAffineTransformations {
    fun affineTransformation(matA: MatrixTwoDim, matB: MatrixTwoDim): MatrixTwoDim {
        validateIsCube(matB)
        validateMat(matA)
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
        val normalizedCorrectly = rawMult.values[3].all { x -> x == 1.0F }
        if (!normalizedCorrectly){
            throw Exception("failed normalizing matrix")
        }
        return rawMult
    }

    fun validateMat(mat: MatrixTwoDim){
        if (mat.height != 4){
            throw Exception("Invalid input matrix passed, input matrix should have a size 4xN")
        }
    }

    fun validateIsCube(mat: MatrixTwoDim){
        if (mat.height != 4) {
            throw Exception("Invalid input matrix passed, matrix deoes not reresent a cube, failed height")
        }
        if (mat.width != 8) {
            throw Exception("Invalid input matrix passed, matrix deoes not reresent a cube, failed width")
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
        val angleRad = -MathUtils.degToRad(angleDeg.toFloat()) // we invert the angle since we want y axis to also follow right hand rule
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

    fun rotateWithQuaternion(mat: MatrixTwoDim, w: Float, x: Float, y: Float, z: Float): MatrixTwoDim {
        val w_sq = w*w
        val x_sq = x*x
        val y_sq = y*y
        val z_sq = z*z
        val rawRotationMat: MutableList<MutableList<Float>> = listOf<List<Float>>(
            listOf(w_sq + x_sq - y_sq - z_sq, 2*x*y - 2*w*z, 2*x*z + 2*w*y, 0F),
            listOf(2*x*y + 2*w*z, w_sq + y_sq - x_sq - z_sq, 2*y*z - 2*w*x, 0F),
            listOf(2*x*z - 2*w*y, 2*y*z + 2*w*x, w_sq + z_sq - x_sq - y_sq, 0F),
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