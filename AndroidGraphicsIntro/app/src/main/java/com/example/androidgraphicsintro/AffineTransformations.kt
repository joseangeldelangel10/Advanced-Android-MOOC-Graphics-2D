package com.example.androidgraphicsintro

object AffineTransformations {
    fun affineTransformation(matA: MatrixTwoDim, matB: MatrixTwoDim): MatrixTwoDim{
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
}