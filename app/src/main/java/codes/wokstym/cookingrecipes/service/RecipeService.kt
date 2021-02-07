package codes.wokstym.cookingrecipes.service

import codes.wokstym.cookingrecipes.models.IngredientDto
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.models.ShoppingListDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.*

interface RecipeService {

    @GET("/ingredient/{id}")
    fun getIngredient(@Path("id") id: UUID?): Call<IngredientDto>

    @GET("/recipe/all")
    fun getRecipes(): Call<List<RecipeDto>>

    @POST("/list/")
    fun getShoppingList(@Body uuidsOfRecipes: List<UUID>): Call<ShoppingListDto>
}
