package codes.wokstym.cookingrecipes.models

import java.io.Serializable
import java.math.BigDecimal
import java.util.*

class RecipeDto(val id: UUID,
                val preparation: String,
                val ingredients: List<IngredientDto>,
                val mealTime: MealTime,
                val prepTime: Int,
                val price: BigDecimal,
                val title: String,
                val pictureUrl: String) : Serializable