package com.ludovic.vimont.randomcocktail.model

data class DrinkItem(
	val strDrinkFR: Any? = null,
	val strInstructionsDE: String? = null,
	val strDrinkZHHANS: Any? = null,
	val strDrinkZHHANT: Any? = null,
	val strIngredient10: Any? = null,
	val strDrink: String? = null,
	val strIngredient12: Any? = null,
	val strIngredient11: Any? = null,
	val strIngredient14: Any? = null,
	val strCategory: String? = null,
	val strAlcoholic: String? = null,
	val strIngredient13: Any? = null,
	val strIngredient15: Any? = null,
	val strCreativeCommonsConfirmed: String? = null,
	val strIBA: Any? = null,
	val strDrinkES: Any? = null,
	val strVideo: Any? = null,
	val strTags: Any? = null,
	val strInstructions: String? = null,
	val strIngredient1: String? = null,
	val strIngredient3: String? = null,
	val strIngredient2: String? = null,
	val strIngredient5: String? = null,
	val strIngredient4: String? = null,
	val strIngredient7: String? = null,
	val strIngredient6: String? = null,
	val strIngredient9: Any? = null,
	val strInstructionsFR: Any? = null,
	val strIngredient8: Any? = null,
	val idDrink: String? = null,
	val strInstructionsES: Any? = null,
	val strGlass: String? = null,
	val strDrinkDE: Any? = null,
	val strMeasure12: Any? = null,
	val strMeasure13: Any? = null,
	val strMeasure10: Any? = null,
	val strMeasure11: Any? = null,
	val dateModified: String? = null,
	val strDrinkAlternate: Any? = null,
	val strDrinkThumb: String? = null,
	val strInstructionsZHHANT: Any? = null,
	val strMeasure9: Any? = null,
	val strMeasure7: String? = null,
	val strMeasure8: Any? = null,
	val strMeasure5: String? = null,
	val strMeasure6: String? = null,
	val strMeasure3: String? = null,
	val strMeasure4: String? = null,
	val strMeasure1: String? = null,
	val strMeasure2: String? = null,
	val strInstructionsZHHANS: Any? = null,
	val strMeasure14: Any? = null,
	val strMeasure15: Any? = null
) {
	private val TAG = DrinkItem::javaClass.name

	fun getName(): String {
		strDrink?.let {
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

	override fun toString(): String {
		return "$TAG [ id=$idDrink, name=$strDrink, img=$strDrinkThumb ]"
	}
}