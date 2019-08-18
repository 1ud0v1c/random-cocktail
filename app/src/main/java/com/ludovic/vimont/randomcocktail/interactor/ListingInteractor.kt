package com.ludovic.vimont.randomcocktail.interactor

import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.ludovic.vimont.randomcocktail.model.APIResponse
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ListingInteractor {
    private val ROOT_URL_COCKTAIL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail"
    private val ROOT_URL_ORDINARY_DRINK = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink"
    private val gson = Gson()

    interface OnListingFinishedListener {
        fun onSuccess(drinks: List<DrinkItem>)
        fun onFail(error: String)
    }

    // TODO: improve readability by using suspending function
    // TODO: add click on item, @see: https://www.thecocktaildb.com/api/json/v1/1/lookup.php?i=11007
    fun listing(onListingFinishedListener: OnListingFinishedListener) {
        GlobalScope.launch(Dispatchers.Default) {
            val drinks = ArrayList<DrinkItem>()

            coroutineScope {
                launch {
                    val (_, response, result) = ROOT_URL_COCKTAIL
                        .httpGet()
                        .responseString()

                    val statusCode = response.statusCode

                    println(statusCode)
                    println(result.component1())
                    println(result.component2())
                    println(result.get())

                    val apiResponse = gson.fromJson(result.get(), APIResponse::class.java)
                    if (apiResponse.drinks != null) {
                        drinks.addAll(apiResponse.drinks)
                    }
                }

                launch {
                    val (_, response, result) = ROOT_URL_ORDINARY_DRINK
                        .httpGet()
                        .responseString()

                    val statusCode = response.statusCode

                    println(statusCode)
                    println(result.component1())
                    println(result.component2())
                    println(result.get())

                    val apiResponse = gson.fromJson(result.get(), APIResponse::class.java)
                    if (apiResponse.drinks != null) {
                        drinks.addAll(apiResponse.drinks)
                    }
                }
            }

            drinks.sortBy { it.strDrink }

            launch(Dispatchers.Main) {
                println("--------------- ${drinks.size} ---------------")
                onListingFinishedListener.onSuccess(drinks)
                /* if (apiResponse.drinks != null) {
                   println("--------------- ${apiResponse.drinks.size} ---------------")
                   onListingFinishedListener.onSuccess(apiResponse.drinks)
               } else {
                   onListingFinishedListener.onFail("$statusCode")
               } */
            }
        }
    }
}