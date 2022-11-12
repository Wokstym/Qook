package codes.wokstym.cookingrecipes.service

import java.math.BigDecimal

data class NewRecipeDto(
    val title: String,
    val preparation: String,
    val prepTime: Int,
    val price: BigDecimal,
    val mealTime: String,
    val ingredients: List<NewIngredientQuantityDto>
)

data class NewIngredientQuantityDto(
    val name: String,
    val amount: Int,
    val unit: Unit,
)