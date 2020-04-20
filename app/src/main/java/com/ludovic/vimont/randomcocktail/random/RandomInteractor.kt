package com.ludovic.vimont.randomcocktail.random

import android.content.Context
import android.content.Intent
import com.github.kittinunf.fuel.httpGet
import com.ludovic.vimont.randomcocktail.helper.GsonHelper
import com.ludovic.vimont.randomcocktail.helper.NetworkHelper
import com.ludovic.vimont.randomcocktail.model.APIResponse
import com.ludovic.vimont.randomcocktail.model.Constants
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import kotlinx.coroutines.*

class RandomInteractor {
    interface OnRandomFinishedListener {
        fun onSuccess(drinkItem: DrinkItem)
        fun onFail(error: String)
    }

    fun random(context: Context, intent: Intent, onRandomFinishedListener: OnRandomFinishedListener) {
        GlobalScope.launch(Dispatchers.Default) {
            if (intent.hasExtra(Constants.KEY_DRINK_ITEM)) {
                loadFromIntent(intent, onRandomFinishedListener)
            } else {
                loadFromServer(context, onRandomFinishedListener)
            }
        }
    }

    private fun CoroutineScope.loadFromIntent(intent: Intent, onRandomFinishedListener: OnRandomFinishedListener) {
        val drinkItem: DrinkItem? =
            intent.getSerializableExtra(Constants.KEY_DRINK_ITEM) as DrinkItem?
        launch(Dispatchers.Main) {
            drinkItem?.let {
                onRandomFinishedListener.onSuccess(drinkItem)
            }
        }
    }

    private fun CoroutineScope.loadFromServer(context: Context, onRandomFinishedListener: OnRandomFinishedListener) {
        if (!NetworkHelper.isOnline(context)) {
            onRandomFinishedListener.onFail("You need to activate internet first.")
            coroutineContext.cancel()
        }
        val drinkItem: DrinkItem? = fetchRandomDrink(Constants.ROOT_URL_RANDOM_COCKTAIL)
        launch(Dispatchers.Main) {
            if (drinkItem != null) {
                onRandomFinishedListener.onSuccess(drinkItem)
            } else {
                onRandomFinishedListener.onFail("An error occurred while fetching a random cocktail.")
            }
        }
    }

    private fun fetchRandomDrink(url: String): DrinkItem? {
        val (_, response, result) = url
            .httpGet()
            .responseString()

        val statusCode = response.statusCode
        println(statusCode)
        println(result.get())

        val apiResponse = GsonHelper.fromJson(result.get(), APIResponse::class.java)
        if (apiResponse.drinks != null && apiResponse.drinks.isNotEmpty()) {
            return apiResponse.drinks[0]
        }
        return null
    }
}