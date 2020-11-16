package codes.wokstym.cookingrecipes.service;

import java.util.List;
import java.util.UUID;

import codes.wokstym.cookingrecipes.models.IngredientDto;
import codes.wokstym.cookingrecipes.models.RecipeDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RecipeService {

    @GET("/ingredient/{id}")
    Call<IngredientDto> getIngredient(@Path("id") UUID id);

    @GET("/recipe/all")
    Call<List<RecipeDto>> getRecipes();

}
