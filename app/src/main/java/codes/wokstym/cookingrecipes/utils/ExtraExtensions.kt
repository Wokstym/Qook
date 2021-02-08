package codes.wokstym.cookingrecipes.utils

import android.content.Intent
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.models.ShoppingListDto

const val RECIPE_EXTRA = "recipe_extra"
const val SHOPPING_LIST_EXTRA = "shopping_list_extra"

fun Intent.getShoppingListExtra() = getSerializableExtra(SHOPPING_LIST_EXTRA) as ShoppingListDto
fun Intent.putShoppingListExtra(shoppingList: ShoppingListDto) = putExtra(SHOPPING_LIST_EXTRA, shoppingList)

fun Intent.getRecipeExtra() = getSerializableExtra(RECIPE_EXTRA) as RecipeDto
fun Intent.putRecipeExtra(recipe: RecipeDto) = putExtra(RECIPE_EXTRA, recipe)