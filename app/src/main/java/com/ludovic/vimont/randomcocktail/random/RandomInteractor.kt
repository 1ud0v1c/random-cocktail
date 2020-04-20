package com.ludovic.vimont.randomcocktail.random

import android.content.Context
import android.content.Intent
import com.github.kittinunf.fuel.httpGet
import com.ludovic.vimont.randomcocktail.helper.GsonHelper
import com.ludovic.vimont.randomcocktail.helper.NetworkHelper
import com.ludovic.vimont.randomcocktail.model.APIResponse
import com.ludovic.vimont.randomcocktail.model.Constants
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class RandomInteractor {
    private val ROOT_URL_RANDOM_COCKTAIL = "https://www.thecocktaildb.com/api/json/v1/1/random.php"

    interface OnRandomFinishedListener {
        fun onSuccess(drinkItem: DrinkItem)
        fun onFail(error: String)
    }

    fun random(
        context: Context,
        intent: Intent,
        onRandomFinishedListener: OnRandomFinishedListener
    ) {
        GlobalScope.launch(Dispatchers.Default) {
            if (intent.hasExtra(Constants.keyDrinkItem)) {
                val drinkItem: DrinkItem? = intent.getSerializableExtra(Constants.keyDrinkItem) as DrinkItem?
                launch(Dispatchers.Main) {
                    drinkItem?.let {
                        println(it.strInstructions)
                        onRandomFinishedListener.onSuccess(drinkItem)
                    }
                }
            } else {
                if (!NetworkHelper.isOnline(context)) {
                    onRandomFinishedListener.onFail("You need to activate internet first.")
                    coroutineContext.cancel()
                }

                val drinkItem: DrinkItem? = getRandomDrink(ROOT_URL_RANDOM_COCKTAIL)
                launch(Dispatchers.Main) {
                    if (drinkItem != null) {
                        onRandomFinishedListener.onSuccess(drinkItem)
                    } else {
                        onRandomFinishedListener.onFail("An error occurred while fetching a random cocktail.")
                    }
                }
            }
        }
    }

    private fun getRandomDrink(url: String): DrinkItem? {
        val (_, response, result) = url
            .httpGet()
            .responseString()

        val statusCode = response.statusCode
        println(statusCode)
        println(result.component1())
        println(result.component2())
        println(result.get())

        val apiResponse = GsonHelper.fromJson(result.get(), APIResponse::class.java)
        if (apiResponse.drinks != null && apiResponse.drinks.isNotEmpty()) {
            return apiResponse.drinks[0]
        }
        return null
    }
}