package codes.wokstym.cookingrecipes.tasks

import android.util.Log
import codes.wokstym.cookingrecipes.models.ShoppingListDto
import codes.wokstym.cookingrecipes.service.RecipeService
import codes.wokstym.cookingrecipes.views.RecipeListActivity
import retrofit2.Call
import retrofit2.Response
import java.util.*


class GetShoppingListTask(private val responseActivity: RecipeListActivity) : BackendTask<ShoppingListDto, RecipeService>() {

    fun execute(uuidsOfRecipes: List<UUID>) {
        callTask(RecipeService::class.java) { it.getShoppingList(uuidsOfRecipes) }
    }

    override fun onResponse(call: Call<ShoppingListDto>, response: Response<ShoppingListDto>) {
        if (response.isSuccessful) {
            val shoppingList = response.body()!!
            responseActivity.showShoppingList(shoppingList)
        } else {
            Log.d("Przepis", response.errorBody().toString())
        }
        responseActivity.hideProgress()
    }

    override fun onFailure(call: Call<ShoppingListDto>, t: Throwable) {
        Log.d("Przepis", "onFailure: $call")
        t.printStackTrace()
        responseActivity.hideProgress()
    }
}
