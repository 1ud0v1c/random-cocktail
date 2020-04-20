package com.ludovic.vimont.randomcocktail.listing

import android.content.Context
import com.ludovic.vimont.randomcocktail.listing.ListingInteractor
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.listing.ListingView

class ListingPresenter(private var listingView: ListingView,
                       private val listingInteractor: ListingInteractor): ListingInteractor.OnListingFinishedListener {
    fun loadDrinks(context: Context) {
        listingInteractor.listing(context, this)
    }

    override fun onSuccess(drinks: List<DrinkItem>) {
        listingView.setCocktails(drinks)
    }

    // TODO
    override fun onFail(error: String) {
        println(error)
    }
}