package codes.wokstym.cookingrecipes.models

import java.io.Serializable
import java.util.*

class IngredientDto(
        val id: UUID,
        val name: String,
        val unit: Unit,
        val amount: Int) : Serializable
