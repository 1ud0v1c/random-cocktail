package com.ludovic.vimont.randomcocktail.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.interactor.RandomInteractor
import com.ludovic.vimont.randomcocktail.presenter.RandomPresenter
import com.ludovic.vimont.randomcocktail.view.RandomView

class RandomActivity : AppCompatActivity(), RandomView {
    private val randomPresenter = RandomPresenter(this, RandomInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)
        onCreate()
    }

    override fun onCreate() {
        randomPresenter.onCreate(applicationContext)
    }
}