package com.ludovic.vimont.randomcocktail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ludovic.vimont.randomcocktail.activities.ListingActivity
import com.ludovic.vimont.randomcocktail.activities.RandomActivity

/*
 * Data loaded from the following API: @see: https://www.thecocktaildb.com/api.php
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_listing_cocktails).setOnClickListener {
            val intent = Intent(this, ListingActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button_random_cocktail).setOnClickListener {
            val intent = Intent(this, RandomActivity::class.java)
            startActivity(intent)
        }
    }
}