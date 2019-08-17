package com.ludovic.vimont.randomcocktail.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ludovic.vimont.randomcocktail.R

class RandomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        // TODO: https://www.thecocktaildb.com/api/json/v1/1/random.php
    }
}