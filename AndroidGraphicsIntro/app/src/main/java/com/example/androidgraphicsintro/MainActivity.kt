package com.example.androidgraphicsintro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    var myView: MyView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        // replace the view with my custom designed view
        myView = MyView(this, null)
        setContentView(myView)
    }
}