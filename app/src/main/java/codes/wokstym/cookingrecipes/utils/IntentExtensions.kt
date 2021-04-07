package codes.wokstym.cookingrecipes.utils

import android.content.Context
import android.content.Intent
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.models.ShoppingListDto
import codes.wokstym.cookingrecipes.views.AddRecipeActivity
import codes.wokstym.cookingrecipes.views.RecipeDetailsActivity
import codes.wokstym.cookingrecipes.views.ShoppingListActivity

fun Context.startAddRecipeActivity() {
    Intent(this, AddRecipeActivity::class.java)
            .let(this::startActivity)
}

fun Context.startShoppingListActivity(shoppingList: ShoppingListDto, recipes: ArrayList<RecipeDto>) {
    Intent(this, ShoppingListActivity::class.java)
            .apply {
                putShoppingListExtra(shoppingList)
                putRecipesListExtra(recipes)
            }
            .let(this::startActivity)
}

fun Context.startRecipeDetailsActivity(recipe: RecipeDto) {
    Intent(this, RecipeDetailsActivity::class.java)
            .apply { putRecipeExtra(recipe) }
            .let(this::startActivity)
}