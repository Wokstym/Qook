package codes.wokstym.cookingrecipes.models

import java.io.Serializable

class ShoppingListDto(val ingredientList: List<IngredientDto>) : Serializable {

    override fun toString(): String {
        val result: StringBuilder = StringBuilder()

        ingredientList.forEach {
            result.append(it.name)
                    .append(" - ")
                    .append(it.name)
                    .append(" ")
                    .append(it.unit.polishName)
                    .append(" \n")
        }
        return result.toString()
    }
}
