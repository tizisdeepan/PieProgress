package com.deepan.pieprogress

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import kotlin.properties.Delegates
import android.animation.AnimatorSet
import android.animation.PropertyValuesHolder


@Suppress("DEPRECATION")
class PieProgress : View {

    private var progressColor = Color.parseColor("#ffffff")
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val tickPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect: RectF by lazy {
        RectF(0f, 0f, width.toFloat() - strokePaint.strokeWidth, height.toFloat() - strokePaint.strokeWidth)
    }
    private var progress = 0f

    var isCompleted: Boolean by Delegates.observable(false) { _, _, newValue ->
        if (newValue) invalidate()
    }

    constructor(context: Context) : super(context) {
        init(context, null, -1, -1)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs, -1, -1)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr, -1)
    }

    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        val attributes = context.theme.obtainStyledAttributes(attrs, R.styleable.PieProgress, defStyleAttr, defStyleRes)
        try {
            progressColor = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                attributes.getColor(
                    R.styleable.PieProgress_progressColor, context.resources.getColor(R.color.white, context.theme)
                )
            } else {
                attributes.getColor(
                    R.styleable.PieProgress_progressColor, context.resources.getColor(R.color.white)
                )
            }
            progressPaint.apply {
                color = progressColor
            }
            tickPaint.apply {
                color = progressColor
                style = Paint.Style.STROKE
                strokeCap = Paint.Cap.ROUND
                strokeWidth = resources.getDimension(R.dimen.width_circle_stroke)
            }
            strokePaint.apply {
                color = progressColor
                style = Paint.Style.STROKE
                strokeWidth = resources.getDimension(R.dimen.width_circle_stroke)
            }
        } finally {
            attributes.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        if (!isCompleted) {
            canvas?.drawCircle(
                rect.centerX(), rect.centerY(), (width / 2 - strokePaint.strokeWidth), strokePaint
            )
            canvas?.drawArc(rect, 270f, (progress * 3.6).toFloat(), true, progressPaint)
        } else {
            canvas?.drawCircle(
                rect.centerX(), rect.centerY(), (width / 2 - strokePaint.strokeWidth), strokePaint
            )
            canvas?.drawLine(
                width / 4f, (height / 2f) + 10f, (width / 8f) * 3.5f, ((height / 8f) * 5f) + 10f, tickPaint
            )
            canvas?.drawLine(
                (width / 8f) * 3.5f,
                ((height / 8f) * 5f) + 10f,
                (width / 4f) * 3f,
                ((height / 4f) * 1f) + 10f,
                tickPaint
            )
        }
        super.onDraw(canvas)
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        invalidate()
        if (progress.toInt() == 100) {
            val firstBounce = ObjectAnimator.ofPropertyValuesHolder(
                this, PropertyValuesHolder.ofFloat(SCALE_X, 1.1f), PropertyValuesHolder.ofFloat(SCALE_Y, 1.1f)
            ).setDuration(100)
            val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                this, PropertyValuesHolder.ofFloat(SCALE_X, 0f), PropertyValuesHolder.ofFloat(SCALE_Y, 0f)
            ).setDuration(200)
            val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                this, PropertyValuesHolder.ofFloat(SCALE_X, 1f), PropertyValuesHolder.ofFloat(SCALE_Y, 1f)
            ).setDuration(200)
            scaleUp.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {}
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {
                    isCompleted = true
                }
            })
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(firstBounce, scaleDown, scaleUp)
            animatorSet.start()
        }
    }
}