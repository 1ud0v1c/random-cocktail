package com.ludovic.vimont.randomcocktail.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CocktailAPIResponse(
    val drinks: List<DrinksItem?>? = null
)