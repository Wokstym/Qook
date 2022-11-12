package codes.wokstym.cookingrecipes.service

import codes.wokstym.cookingrecipes.BuildConfig
import codes.wokstym.cookingrecipes.models.IngredientDto
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.models.ShoppingListDto
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.*

interface RecipeService {

    @GET("/ingredient/{id}")
    fun getIngredient(@Path("id") id: UUID?): Call<IngredientDto>

    @GET("/recipe/all")
    fun getRecipes(): Call<List<RecipeDto>>

    @GET("/recipe/mealtimes")
    suspend fun getMealTimes(): List<String>

    @POST("/list/")
    fun getShoppingList(@Body uuidsOfRecipes: List<UUID>): Call<ShoppingListDto>

    @Multipart
    @POST("/recipe/")
    fun addRecipe(
        @Part("recipeDto") recipeDto: NewRecipeDto,
        @Part image: MultipartBody.Part?
    ): Call<RecipeDto>
}


object RetrofitBuilder {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.RecipesURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService: RecipeService = getRetrofit().create(RecipeService::class.java)
}