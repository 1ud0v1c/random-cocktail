package com.ludovic.vimont.randomcocktail

import android.app.Application
import android.graphics.Bitmap
import com.squareup.picasso.Picasso

class RandomCocktailApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Picasso.setSingletonInstance(
            Picasso.Builder(applicationContext)
                .defaultBitmapConfig(Bitmap.Config.RGB_565)
                .build()
        )
    }
}