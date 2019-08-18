package com.ludovic.vimont.randomcocktail.model

data class Ingredient(private val name: String,
                      private val measure: String) {
    fun getName(): String {
        return name
    }

    fun getMeasure(): String {
        return measure
    }
}