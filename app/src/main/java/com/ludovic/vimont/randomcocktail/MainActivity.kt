package com.ludovic.vimont.randomcocktail

import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.ludovic.vimont.randomcocktail.listing.ListingActivity
import com.ludovic.vimont.randomcocktail.random.RandomActivity
import com.ludovic.vimont.randomcocktail.helper.ViewHelper

/*
 * Data loaded from the following API: @see: https://www.thecocktaildb.com/api.php
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonListingCocktails: LinearLayout = findViewById(R.id.linear_layout_listing_cocktails)
        onButtonClick(buttonListingCocktails) {
            val intent = Intent(this, ListingActivity::class.java)
            startActivity(intent)
        }

        val buttonRandomCocktail: LinearLayout = findViewById(R.id.linear_layout_random_cocktail)
        onButtonClick(buttonRandomCocktail) {
            val intent = Intent(this, RandomActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onButtonClick(viewGroup: ViewGroup, block: () -> Unit) {
        val leftImageView: ImageView = viewGroup[0] as ImageView
        val buttonView: Button = viewGroup[1] as Button
        viewGroup.setOnTouchListener { _, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    ViewHelper.setTextColor(applicationContext, buttonView, R.color.material_color_black)
                    val porterDuffColorFilter = PorterDuffColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                    leftImageView.colorFilter = porterDuffColorFilter
                    viewGroup.setBackgroundResource(R.drawable.button_transparent_white_border_hover)
                    viewGroup.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    ViewHelper.setTextColor(applicationContext, buttonView, R.color.material_color_white)
                    val porterDuffColorFilter = PorterDuffColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
                    leftImageView.colorFilter = porterDuffColorFilter
                    viewGroup.setBackgroundResource(R.drawable.button_transparent_white_border)
                    viewGroup.invalidate()
                    block()
                }
            }
            true
        }
    }
}