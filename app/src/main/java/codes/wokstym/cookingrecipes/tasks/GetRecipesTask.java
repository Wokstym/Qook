package codes.wokstym.cookingrecipes.tasks;

import android.util.Log;

import java.util.List;

import codes.wokstym.cookingrecipes.MainActivity;
import codes.wokstym.cookingrecipes.models.RecipeDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetRecipesTask implements Callback<List<RecipeDto>> {
    private MainActivity responseActivity;

    public GetRecipesTask(MainActivity responseActivity) {
        this.responseActivity = responseActivity;
    }


    @Override
    public void onResponse(Call<List<RecipeDto>> call, Response<List<RecipeDto>> response) {
        if (response.isSuccessful()) {
            List<RecipeDto> changesList = response.body();
            Log.d("Przepis", changesList.toString());
            responseActivity.showRecipes(changesList);
        } else {
            Log.d("Przepis", String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<List<RecipeDto>> call, Throwable t) {
        Log.d("Przepis", "onFailure: " + call);
        t.printStackTrace();
    }
}
