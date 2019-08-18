package com.ludovic.vimont.randomcocktail.presenter

import android.content.Context
import com.ludovic.vimont.randomcocktail.interactor.RandomInteractor
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.view.RandomView

class RandomPresenter(private val randomView: RandomView,
                      private val randomInteractor: RandomInteractor): RandomInteractor.OnRandomFinishedListener {
    fun onCreate(context: Context) {
        randomInteractor.random(context, this)
    }

    // TODO:
    override fun onSuccess(drink: DrinkItem) {
        println(drink)
    }

    // TODO
    override fun onFail(error: String) {
        println(error)
    }
}