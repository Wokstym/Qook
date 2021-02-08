package codes.wokstym.cookingrecipes.utils

import android.content.Context
import android.content.Intent
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.models.ShoppingListDto
import codes.wokstym.cookingrecipes.views.RecipeDetailsActivity
import codes.wokstym.cookingrecipes.views.RecipeListActivity
import codes.wokstym.cookingrecipes.views.ShoppingListActivity

fun Context.startRecipeListActivity() {
    val intent = Intent(this, RecipeListActivity::class.java)
    startActivity(intent)
}

fun Context.startShoppingListActivity(shoppingList: ShoppingListDto) {
    Intent(this, ShoppingListActivity::class.java)
            .apply { putShoppingListExtra(shoppingList) }
            .let(this::startActivity)
}

fun Context.startRecipeDetailsActivity(recipe: RecipeDto) {
    Intent(this, RecipeDetailsActivity::class.java)
            .apply { putRecipeExtra(recipe) }
            .let(this::startActivity)
}