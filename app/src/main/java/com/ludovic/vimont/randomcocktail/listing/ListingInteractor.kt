package com.ludovic.vimont.randomcocktail.listing

import android.content.Context
import com.google.gson.Gson
import com.ludovic.vimont.randomcocktail.helper.FileHelper
import com.ludovic.vimont.randomcocktail.helper.GsonHelper
import com.ludovic.vimont.randomcocktail.helper.fromJson
import com.ludovic.vimont.randomcocktail.model.Constants
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import kotlinx.coroutines.*

class ListingInteractor {
    interface OnListingFinishedListener {
        fun onSuccess(drinks: List<DrinkItem>)

        fun onFail(error: String)
    }

    // TODO: add database
    fun listing(context: Context, onListingFinishedListener: OnListingFinishedListener) {
        GlobalScope.launch(Dispatchers.Default) {
            val drinks = ArrayList<DrinkItem>()
            val jsonContent = FileHelper.readFileFromAssets(context, Constants.ASSETS_FILENAME)
            drinks.addAll(Gson().fromJson(jsonContent))
            drinks.sortBy {
                it.strDrink
            }

            launch(Dispatchers.Main) {
                if (drinks.size > 0) {
                    GlobalScope.launch(Dispatchers.Default) {
                        FileHelper.writeToFile(context, "drinks.json", GsonHelper.gson.toJson(drinks))
                    }
                    onListingFinishedListener.onSuccess(drinks)
                } else {
                    onListingFinishedListener.onFail("An error occurred while fetching the data.")
                }
            }
        }
    }
}