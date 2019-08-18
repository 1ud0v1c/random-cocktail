package com.ludovic.vimont.randomcocktail.view

import com.ludovic.vimont.randomcocktail.model.DrinkItem

interface RandomView {
    fun onCreate()

    fun displayCocktail(drinkItem: DrinkItem)
}