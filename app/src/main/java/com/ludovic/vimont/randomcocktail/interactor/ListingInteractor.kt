package com.ludovic.vimont.randomcocktail.interactor

import android.content.Context
import com.github.kittinunf.fuel.httpGet
import com.ludovic.vimont.randomcocktail.helper.GsonHelper
import com.ludovic.vimont.randomcocktail.helper.NetworkHelper
import com.ludovic.vimont.randomcocktail.model.APIResponse
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import kotlinx.coroutines.*

class ListingInteractor {
    private val ROOT_URL_COCKTAIL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail"
    private val ROOT_URL_ORDINARY_DRINK = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink"

    interface OnListingFinishedListener {
        fun onSuccess(drinks: List<DrinkItem>)
        fun onFail(error: String)
    }

    // TODO: add database
    // TODO: add click on item, @see: https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
    fun listing(context: Context, onListingFinishedListener: OnListingFinishedListener) {
        GlobalScope.launch(Dispatchers.Default) {
            val drinks = ArrayList<DrinkItem>()

            if (!NetworkHelper.isOnline(context)) {
                onListingFinishedListener.onFail("You need to activate internet first.")
                coroutineContext.cancel()
            }

            coroutineScope {
                launch {
                    drinks.addAll(getDrinks(ROOT_URL_COCKTAIL))
                }
                launch {
                    drinks.addAll(getDrinks(ROOT_URL_ORDINARY_DRINK))
                }
            }

            drinks.sortBy { it.strDrink }

            launch(Dispatchers.Main) {
                if (drinks.size > 0) {
                    onListingFinishedListener.onSuccess(drinks)
                } else {
                    onListingFinishedListener.onFail("An error occurred while fetching the data.")
                }
            }
        }
    }

    // TODO: handle error
    private fun getDrinks(url: String): List<DrinkItem> {
        val (_, response, result) = url
            .httpGet()
            .responseString()

        val statusCode = response.statusCode
        println(statusCode)
        println(result.component1())
        println(result.component2())
        println(result.get())

        val apiResponse = GsonHelper.fromJson(result.get(), APIResponse::class.java)
        if (apiResponse.drinks != null) {
            return apiResponse.drinks
        }
        return ArrayList()
    }
}