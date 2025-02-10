package com.example.androidgraphicsintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidgraphicsintro.TwoDimViews.PlottingGraphsView

class MainActivity : AppCompatActivity() {
    var myView: MyView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // replace the view with my custom designed view
        // myView = MyView(this, null)
        val view = PlottingGraphsView(this, null)
        setContentView(view)
    }
}