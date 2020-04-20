package com.ludovic.vimont.randomcocktail.listing

import com.ludovic.vimont.randomcocktail.model.DrinkItem

// TODO: add onError
interface ListingView {
    fun onCreate()

    fun setCocktails(drinks: List<DrinkItem>)
}