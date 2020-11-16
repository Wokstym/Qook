package codes.wokstym.cookingrecipes.service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.UUID;

import codes.wokstym.cookingrecipes.BuildConfig;
import codes.wokstym.cookingrecipes.models.IngredientDto;
import codes.wokstym.cookingrecipes.models.RecipeDto;
import codes.wokstym.cookingrecipes.tasks.GetIngredientTask;
import codes.wokstym.cookingrecipes.tasks.GetRecipesTask;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeServiceImpl {

    public void getIngredient(UUID id, GetIngredientTask callback) {
        RecipeService service = prepareRetrofit().create(RecipeService.class);

        Call<IngredientDto> xd = service.getIngredient(id);
        xd.enqueue(callback);
    }

    public void getRecipes(GetRecipesTask callback) {
        RecipeService service = prepareRetrofit().create(RecipeService.class);

        Call<List<RecipeDto>> xd = service.getRecipes();
        xd.enqueue(callback);
    }

    public Retrofit prepareRetrofit() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .create();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.RecipesURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
