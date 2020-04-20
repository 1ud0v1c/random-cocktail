package com.ludovic.vimont.randomcocktail.helper

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

object ViewHelper {
    fun setTextColor(context: Context, view: View, color: Int) {
        if (view is Button) {
            val button: Button = view
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                button.setTextColor(ContextCompat.getColor(context, color))
            } else {
                button.setTextColor(context.resources.getColor(color))
            }
        }
        if (view is TextView) {
            val textView: TextView = view
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                textView.setTextColor(ContextCompat.getColor(context, color))
            } else {
                textView.setTextColor(context.resources.getColor(color))
            }
        }
    }

    fun setBackgroundColor(context: Context, view: View, color: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            view.setBackgroundColor(ContextCompat.getColor(context, color))
        } else {
            view.setBackgroundColor(context.resources.getColor(color))
        }
    }
}