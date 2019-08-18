package com.ludovic.vimont.randomcocktail.view

import com.ludovic.vimont.randomcocktail.model.DrinkItem

interface RandomView {
    fun onCreate()

    fun onRefresh()

    fun displayCocktail(drinkItem: DrinkItem)
}