package com.ludovic.vimont.randomcocktail.helper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ludovic.vimont.randomcocktail.model.DrinkItem

inline fun <reified T> Gson.fromJson(json: String): T = this.fromJson<T>(json, object: TypeToken<T>() {}.type)

class GsonHelper {
    companion object {
        val gson = Gson()


        fun <T> fromJson(json: String , classOfT: Class<T>): T {
            return gson.fromJson(json, classOfT)
        }
    }
}