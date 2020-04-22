package com.ludovic.vimont.randomcocktail.random

import android.content.Context
import android.content.Intent
import com.ludovic.vimont.randomcocktail.model.DrinkItem

class RandomPresenter(private val randomView: RandomView,
                      private val randomInteractor: RandomInteractor): RandomInteractor.OnRandomFinishedListener {
    fun loadDrink(context: Context, intent: Intent) {
        randomInteractor.random(context, intent, this)
    }

    override fun onSuccess(drinkItem: DrinkItem) {
        randomView.displayCocktail(drinkItem)
    }

    // TODO
    override fun onFail(error: String) {
        println(error)
    }
}