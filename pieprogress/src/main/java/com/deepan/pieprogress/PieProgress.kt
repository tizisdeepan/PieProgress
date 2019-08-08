package com.deepan.pieprogress

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View

@Suppress("DEPRECATION")
class PieProgress : View {

    var progressColor = Color.parseColor("#ffffff")
    private val progressPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val rect: RectF by lazy {
        RectF(0f, 0f, width.toFloat() - strokePaint.strokeWidth, height.toFloat() - strokePaint.strokeWidth)
    }
    private var progress = 0f
    var mListener: PieProgressListener? = null

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
                    R.styleable.PieProgress_progressColor,
                    context.resources.getColor(R.color.white, context.theme)
                )
            } else {
                attributes.getColor(
                    R.styleable.PieProgress_progressColor,
                    context.resources.getColor(R.color.white)
                )
            }
            progressPaint.color = progressColor
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
        canvas?.drawCircle(
            rect.centerX(),
            rect.centerY(),
            (width / 2 - strokePaint.strokeWidth),
            strokePaint
        )
        canvas?.drawArc(rect, 0f, (progress * 3.6).toFloat(), true, progressPaint)
        super.onDraw(canvas)
    }

    fun setProgress(progress: Float) {
        this.progress = progress
        if (progress.toInt() == 100) mListener?.onProgressEnd()
        invalidate()
    }

    fun setListener(progressListener: PieProgressListener) {
        this.mListener = progressListener
    }
}