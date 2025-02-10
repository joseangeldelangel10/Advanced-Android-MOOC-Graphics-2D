package com.example.androidgraphicsintro.Graphics

import java.lang.Exception

enum class MatrixFlatteningMode {
    AVERAGE,
    MAX,
    MIN
}

class MatrixTwoDim {
    val height: Int
    val width: Int
    val values: MutableList<MutableList<Float>>

    constructor(dim1: Int, dim2: Int, vals: MutableList<MutableList<Float>>){
        if (MatrixStatic.isValid(dim1, dim2, vals)){
            height = dim1
            width = dim2
            values = vals
        } else {
            throw Exception("Tried to create invalid matrix")
        }
    }

    fun sumMat(matB : MatrixTwoDim, isSubstraction: Boolean = false): MatrixTwoDim {
        if (matB.height != this.height || matB.width != this.width){
            throw Exception("invalid matrix sum")
        }
        val result = MutableList<MutableList<Float>>(this.height){
            MutableList<Float>(this.width){0.0F}
        }
        for (i in 0 until this.height){
            for (j in 0 until this.width) {
                if (!isSubstraction) {
                    result[i][j] = this.values[i][j] + matB.values[i][j]
                } else {
                    result[i][j] = this.values[i][j] - matB.values[i][j]
                }
            }
        }
        return MatrixTwoDim(this.height, this.width, result)
    }

    fun multMat(matB: MatrixTwoDim): MatrixTwoDim {
        if (this.width != matB.height) {
            throw Exception("invalid matrix multiplication")
        }
        val resultHeight: Int = this.height
        val resultWidth: Int = matB.width
        var result: MutableList<MutableList<Float>> = MutableList(resultHeight){
            MutableList(resultWidth){ 0.0F }
        }
        for (i in 0 until resultHeight){
            for (j in 0 until resultWidth){
                var temp: Float = 0.0F
                 for (k in 0 until this.width) {
                     temp += this.values[i][k]*matB.values[k][j]
                 }
                result[i][j] = temp
            }
        }
        return MatrixTwoDim(resultHeight, resultWidth, result)
    }

    fun transpose(): MatrixTwoDim {
        var result: MutableList<MutableList<Float>> = MutableList(this.width){
            MutableList(this.height){0.0F}
        }
        for (i in 0 until this.height){
            for (j in 0 until this.width){
                result[j][i] = this.values[i][j]
            }
        }
        return MatrixTwoDim(this.width, this.height, result)
    }

    fun flatten(flatteningMode: MatrixFlatteningMode = MatrixFlatteningMode.AVERAGE): List<Float> {
        var result : MutableList<Float> = MutableList(this.width){0F}
        when (flatteningMode){
            MatrixFlatteningMode.AVERAGE -> {
                for (i in 0 until this.height){
                    for (j in 0 until this.width){
                        result[j] += this.values[i][j]
                    }
                }
                return result.map { a -> a/this.height }
            }
            MatrixFlatteningMode.MAX -> throw NotImplementedError()
            MatrixFlatteningMode.MIN -> throw NotImplementedError()
        }
    }


}

object MatrixStatic {
    fun isValid(dim1: Int, dim2: Int, values: List<List<Float>>): Boolean {
        try {
            for (i in 0 until dim1){
                if (values[i].size != dim2) {
                    return false
                }
            }
        } catch (e: Exception){
            return false
        }
        return true
    }
}