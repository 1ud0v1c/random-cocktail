package com.ludovic.vimont.randomcocktail.presenter

import com.ludovic.vimont.randomcocktail.interactor.ListingInteractor
import com.ludovic.vimont.randomcocktail.model.DrinksItem
import com.ludovic.vimont.randomcocktail.view.ListingView

class ListingPresenter(private var listingView: ListingView,
                       private val listingInteractor: ListingInteractor): ListingInteractor.OnListingFinishedListener {
    fun onCreate() {
        listingInteractor.listing(this)
    }

    override fun onSuccess(drinks: List<DrinksItem>) {
        drinks.forEach { drink ->
            println(drink)
        }
    }

    override fun onFail(error: String) {
        println(error)
    }
}