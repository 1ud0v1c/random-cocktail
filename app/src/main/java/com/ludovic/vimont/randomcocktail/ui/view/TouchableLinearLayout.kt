package com.ludovic.vimont.randomcocktail.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.LinearLayout

class TouchableLinearLayout @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0) : LinearLayout(context, attrs, defStyle) {
    override fun onInterceptTouchEvent(motionEvent: MotionEvent): Boolean {
        return true
    }
}