package codes.wokstym.cookingrecipes.tasks;

import android.util.Log;

import java.util.List;

import codes.wokstym.cookingrecipes.MainActivity;
import codes.wokstym.cookingrecipes.models.RecipeDto;
import codes.wokstym.cookingrecipes.service.RecipeService;
import retrofit2.Call;
import retrofit2.Response;


public class GetRecipesTask extends BackendTask<List<RecipeDto>, RecipeService> {
    private final MainActivity responseActivity;

    public GetRecipesTask(MainActivity responseActivity) {
        this.responseActivity = responseActivity;
    }

    public void execute() {
        callTask(RecipeService.class, RecipeService::getRecipes);
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
        responseActivity.hideProgress();
    }

    @Override
    public void onFailure(Call<List<RecipeDto>> call, Throwable t) {
        Log.d("Przepis", "onFailure: " + call);
        t.printStackTrace();
        responseActivity.hideProgress();
    }
}
