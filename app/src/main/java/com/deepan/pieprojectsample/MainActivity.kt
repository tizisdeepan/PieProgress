package com.deepan.pieprojectsample

import android.animation.Animator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.deepan.pieprogress.PieProgressListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pieProgress.setListener(object : PieProgressListener {
            override fun onProgressEnd() {
                Log.e("Progress", "End")
                pieProgress.animate().scaleX(1.1f).scaleY(1.1f).setDuration(100)
                    .setListener(object : Animator.AnimatorListener {
                        override fun onAnimationRepeat(animation: Animator?) {
                        }

                        override fun onAnimationEnd(animation: Animator?) {
                            pieProgress.animate().scaleX(0f).scaleY(0f).setDuration(300).start()
                        }

                        override fun onAnimationCancel(animation: Animator?) {
                        }

                        override fun onAnimationStart(animation: Animator?) {
                        }

                    }).start()
            }
        })
        val handler = Handler()
        for (i in 0..100) {
            handler.postDelayed({
                pieProgress.setProgress(i.toFloat())
            }, 10 * i.toLong())
        }
    }
}