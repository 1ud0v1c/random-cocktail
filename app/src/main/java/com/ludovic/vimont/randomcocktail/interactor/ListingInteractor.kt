package com.ludovic.vimont.randomcocktail.interactor

import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.ludovic.vimont.randomcocktail.model.APIResponse
import com.ludovic.vimont.randomcocktail.model.DrinksItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListingInteractor {
    private val ROOT_URL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail"
    private val gson = Gson()

    interface OnListingFinishedListener {
        fun onSuccess(drinks: List<DrinksItem>)
        fun onFail(error: String)
    }

    fun listing(onListingFinishedListener: OnListingFinishedListener) {
        GlobalScope.launch(Dispatchers.Default) {
            val (_, response, result) = ROOT_URL
                .httpGet()
                .responseString()

            val statusCode = response.statusCode

            println(statusCode)
            println(result.component1())
            println(result.component2())
            println(result.get())

            val apiResponse = gson.fromJson(result.get(), APIResponse::class.java)
            if (apiResponse.drinks != null) {
                onListingFinishedListener.onSuccess(apiResponse.drinks)
            } else {
                onListingFinishedListener.onFail("$statusCode")
            }
        }
    }
}