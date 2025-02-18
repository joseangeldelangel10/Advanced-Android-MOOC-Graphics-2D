package com.example.androidgraphicsintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.ViewAnimator
import com.example.androidgraphicsintro.ThreeDimViews.DrawingCubeView
import com.example.androidgraphicsintro.ThreeDimViews.LeftToRightCubeView
import com.example.androidgraphicsintro.ThreeDimViews.QuaternionRotView
import com.example.androidgraphicsintro.TwoDimViews.PlottingGraphsView

class MainActivity : AppCompatActivity() {
    var myView: MyView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // replace the view with my custom designed view
        // myView = MyView(this, null)
        try {
            val view = QuaternionRotView(this, null)
            setContentView(view)
        } catch (e: Exception){
            val textView = TextView(this)
            textView.text = e.message
            textView.width = this.resources.displayMetrics.widthPixels
            setContentView(textView)
        }
    }
}