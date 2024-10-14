package com.stackhour.lib.sliderbutton

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatButton

@SuppressLint("ClickableViewAccessibility")
class SliderButtonView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    var firstLoad: Boolean = true

    var default_ = 0f
    private var x_axis: Float = default_
    private var y_axis: Float = default_

    init {
        this.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_MOVE -> {
                    val delta = motionEvent.rawX
                    if (delta <= (width - default_) && delta >= default_) {
                        x_axis = delta
                        this.invalidate()
                    }
                }
            }
            true
        }
    }

    private val paint: Paint = Paint().apply {
        color = Color.RED
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (firstLoad) {
            firstLoad = false
            default_ = height * 0.5f
            x_axis = default_
            y_axis = default_
        }
        println("Init Canvas default $default_")
        canvas?.let {
            with(canvas) {
                drawCircle(x_axis, y_axis, height * 0.5f, paint)
            }
        }
    }
}