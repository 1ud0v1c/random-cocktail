package com.ludovic.vimont.randomcocktail.helper

import com.google.gson.Gson

class GsonHelper {
    companion object {
        private val gson = Gson()

        fun <T> fromJson(json: String , classOfT: Class<T>): T {
            return gson.fromJson(json, classOfT)
        }
    }
}