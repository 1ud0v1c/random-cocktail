package com.ludovic.vimont.randomcocktail.listing

import android.content.Context
import android.os.Environment
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.google.gson.Gson
import com.ludovic.vimont.randomcocktail.helper.FileHelper
import com.ludovic.vimont.randomcocktail.helper.GsonHelper
import com.ludovic.vimont.randomcocktail.helper.NetworkHelper
import com.ludovic.vimont.randomcocktail.helper.fromJson
import com.ludovic.vimont.randomcocktail.model.APIResponse
import com.ludovic.vimont.randomcocktail.model.Constants
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import kotlinx.coroutines.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

class ListingInteractor {
    private val ROOT_URL_COCKTAIL = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Cocktail"
    private val ROOT_URL_ORDINARY_DRINK = "https://www.thecocktaildb.com/api/json/v1/1/filter.php?c=Ordinary_Drink"

    interface OnListingFinishedListener {
        fun onSuccess(drinks: List<DrinkItem>)

        fun onFail(error: String)
    }

    // TODO: add database
    fun listing(context: Context, onListingFinishedListener: OnListingFinishedListener) {
        GlobalScope.launch(Dispatchers.Default) {
            val drinks = ArrayList<DrinkItem>()
            val jsonContent = FileHelper.readFileFromAssets(context, Constants.assetsFilename)
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