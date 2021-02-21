package codes.wokstym.cookingrecipes.models

import android.content.Context
import java.io.Serializable

class ShoppingListDto(val ingredientList: List<IngredientDto>) : Serializable {

    fun formatClipboard(context: Context): String =
            ingredientList.joinToString(separator = "\n") {
                "${it.name} - ${it.amount} ${context.getString(it.unit.nameResourceId)}"
            }

}
