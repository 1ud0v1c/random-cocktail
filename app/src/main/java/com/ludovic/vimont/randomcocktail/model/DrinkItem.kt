package com.ludovic.vimont.randomcocktail.model

import com.ludovic.vimont.randomcocktail.helper.ifLet
import java.io.Serializable

data class DrinkItem(
    val idDrink: String? = null,

    // Drink name
    val strDrink: String? = null,
    val strDrinkFR: Any? = null,
    val strDrinkDE: Any? = null,
    val strDrinkES: Any? = null,
    val strDrinkZHHANS: Any? = null,
    val strDrinkZHHANT: Any? = null,

    val strCategory: String? = null,
    val strAlcoholic: String? = null,
    val strCreativeCommonsConfirmed: String? = null,
    val strIBA: Any? = null,
    val strVideo: Any? = null,
    val strTags: Any? = null,
    val strGlass: String? = null,
    val dateModified: String? = null,
    val strDrinkAlternate: Any? = null,
    val strDrinkThumb: String? = null,

    // Instructions
    val strInstructions: String? = null,
    val strInstructionsFR: Any? = null,
    val strInstructionsES: Any? = null,
    val strInstructionsDE: String? = null,
    val strInstructionsZHHANT: Any? = null,
    val strInstructionsZHHANS: Any? = null,

    // Ingredients
    val strIngredient1: String? = null,
    val strIngredient3: String? = null,
    val strIngredient2: String? = null,
    val strIngredient5: String? = null,
    val strIngredient4: String? = null,
    val strIngredient7: String? = null,
    val strIngredient6: String? = null,
    val strIngredient8: String? = null,
    val strIngredient9: String? = null,
    val strIngredient10: String? = null,
    val strIngredient11: String? = null,
    val strIngredient12: String? = null,
    val strIngredient13: String? = null,
    val strIngredient14: String? = null,
    val strIngredient15: String? = null,

    // Measures
    val strMeasure1: String? = null,
    val strMeasure2: String? = null,
    val strMeasure3: String? = null,
    val strMeasure4: String? = null,
    val strMeasure5: String? = null,
    val strMeasure6: String? = null,
    val strMeasure7: String? = null,
    val strMeasure8: String? = null,
    val strMeasure9: String? = null,
    val strMeasure10: String? = null,
    val strMeasure11: String? = null,
    val strMeasure12: String? = null,
    val strMeasure13: String? = null,
    val strMeasure14: String? = null,
    val strMeasure15: String? = null): Serializable {
    private val TAG = DrinkItem::javaClass.name

    fun getName(): String {
        strDrink?.let {
            return it
        }
        return ""
    }

    fun getInstructions(): String {
        strInstructions?.let {
            return it
        }
        return ""
    }

    fun getImage(): String {
        strDrinkThumb?.let {
            return it
        }
        return ""
    }

    // TODO: improve with reflection?
    fun getIngredients(): List<Ingredient> {
        val ingredients = ArrayList<Ingredient>()
        addIngredient(ingredients, strIngredient1, strMeasure1)
        addIngredient(ingredients, strIngredient2, strMeasure2)
        addIngredient(ingredients, strIngredient3, strMeasure3)
        addIngredient(ingredients, strIngredient4, strMeasure4)
        addIngredient(ingredients, strIngredient5, strMeasure5)
        addIngredient(ingredients, strIngredient6, strMeasure6)
        addIngredient(ingredients, strIngredient7, strMeasure7)
        addIngredient(ingredients, strIngredient8, strMeasure8)
        addIngredient(ingredients, strIngredient9, strMeasure9)
        addIngredient(ingredients, strIngredient10, strMeasure10)
        addIngredient(ingredients, strIngredient11, strMeasure11)
        addIngredient(ingredients, strIngredient12, strMeasure12)
        addIngredient(ingredients, strIngredient13, strMeasure13)
        addIngredient(ingredients, strIngredient14, strMeasure14)
        addIngredient(ingredients, strIngredient15, strMeasure15)
        return ingredients
    }

    fun containsAlcohol(): Boolean {
        strAlcoholic?.let { containsAlcohol ->
            return !containsAlcohol.contains("Non")
        }
        return true
    }

    private fun addIngredient(ingredients: ArrayList<Ingredient>, name: String?, measure: String?) {
        ifLet(name, measure) { (name, measure) ->
            if (name.isNotEmpty() && measure.isNotEmpty()) {
                ingredients.add(Ingredient(name.trim(), measure.trim()))
            }
        }
    }

    override fun toString(): String {
        return "$TAG [ id=$idDrink, name=$strDrink, img=$strDrinkThumb ]"
    }
}