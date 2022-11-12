package codes.wokstym.cookingrecipes.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal
import java.util.*

@Parcelize
data class RecipeDto(
    val id: UUID,
    val preparation: String,
    val ingredients: List<IngredientDto>,
    val mealTime: MealTime,
    val prepTime: Int,
    val price: BigDecimal,
    val title: String,
    val pictureUrl: String?
) : Comparable<RecipeDto>, Parcelable {

    fun getPreparations(): List<String> = preparation.split("\n").map { it.trim() }

    override fun compareTo(other: RecipeDto): Int {
        if (this.id == other.id)
            return 0

        if (this.mealTime.order != other.mealTime.order)
            return this.mealTime.order - other.mealTime.order

        return this.title.compareTo(other.title)
    }
}