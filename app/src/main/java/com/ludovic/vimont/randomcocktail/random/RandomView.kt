package com.ludovic.vimont.randomcocktail.random

import com.ludovic.vimont.randomcocktail.model.DrinkItem

interface RandomView {
    fun onCreate()

    fun onRefresh()

    fun displayCocktail(drinkItem: DrinkItem)
}