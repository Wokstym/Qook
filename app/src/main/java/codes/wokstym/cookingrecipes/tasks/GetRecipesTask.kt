package codes.wokstym.cookingrecipes.tasks

import android.util.Log
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.service.RecipeService
import codes.wokstym.cookingrecipes.utils.hideProgressBar
import codes.wokstym.cookingrecipes.views.RecipeListActivity
import retrofit2.Call
import retrofit2.Response

class GetRecipesTask(private val responseActivity: RecipeListActivity)
    : BackendTask<List<RecipeDto>, RecipeService>() {

    fun execute() {
        callTask(RecipeService::class.java) { it.getRecipes() }
    }

    override fun onResponse(call: Call<List<RecipeDto>>, response: Response<List<RecipeDto>>) {
        if (response.isSuccessful) {
            val changesList = response.body()
            Log.d("Przepis", changesList.toString())
            responseActivity.showRecipes(changesList!!)
        } else {
            Log.d("Przepis", response.errorBody().toString())
        }
        responseActivity.hideProgressBar()
    }

    override fun onFailure(call: Call<List<RecipeDto>>, t: Throwable) {
        Log.d("Przepis", "onFailure: $call")
        t.printStackTrace()
        responseActivity.hideProgressBar()
    }
}