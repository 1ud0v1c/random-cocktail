package com.ludovic.vimont.randomcocktail.presenter

import com.ludovic.vimont.randomcocktail.interactor.ListingInteractor
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.view.ListingView

class ListingPresenter(private var listingView: ListingView,
                       private val listingInteractor: ListingInteractor): ListingInteractor.OnListingFinishedListener {
    fun onCreate() {
        listingInteractor.listing(this)
    }

    override fun onSuccess(drinks: List<DrinkItem>) {
        println("--------------- ${drinks.size} ---------------")
        listingView.setCocktails(drinks)
    }

    override fun onFail(error: String) {
        println(error)
    }
}