package com.deepan.pieprojectsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()
        for (i in 0..100) {
            handler.postDelayed({
                pieProgress.setProgress(1f)
            }, 30 * i.toLong())
        }
    }
}
