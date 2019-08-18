package com.ludovic.vimont.randomcocktail.presenter

import android.content.Context
import com.ludovic.vimont.randomcocktail.interactor.RandomInteractor
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.view.RandomView

class RandomPresenter(private val randomView: RandomView,
                      private val randomInteractor: RandomInteractor): RandomInteractor.OnRandomFinishedListener {
    fun loadDrink(context: Context) {
        randomInteractor.random(context, this)
    }

    override fun onSuccess(drinkItem: DrinkItem) {
        println(drinkItem)
        randomView.displayCocktail(drinkItem)
    }

    // TODO
    override fun onFail(error: String) {
        println(error)
    }
}