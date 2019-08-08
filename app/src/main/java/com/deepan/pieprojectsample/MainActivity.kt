package com.deepan.pieprojectsample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Handler()
        for (i in 0..100) {
            handler.postDelayed({
                pieProgress.setProgress(i.toFloat())
            }, 10 * i.toLong())
        }
    }
}