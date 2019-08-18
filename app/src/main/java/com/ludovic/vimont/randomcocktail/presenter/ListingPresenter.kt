package com.ludovic.vimont.randomcocktail.presenter

import android.content.Context
import com.ludovic.vimont.randomcocktail.interactor.ListingInteractor
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.view.ListingView

class ListingPresenter(private var listingView: ListingView,
                       private val listingInteractor: ListingInteractor): ListingInteractor.OnListingFinishedListener {
    fun onCreate(context: Context) {
        listingInteractor.listing(context, this)
    }

    override fun onSuccess(drinks: List<DrinkItem>) {
        println("--------------- ${drinks.size} ---------------")
        listingView.setCocktails(drinks)
    }

    override fun onFail(error: String) {
        println(error)
    }
}