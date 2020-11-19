package codes.wokstym.cookingrecipes.tasks;

import android.util.Log;

import java.util.UUID;

import codes.wokstym.cookingrecipes.MainActivity;
import codes.wokstym.cookingrecipes.models.IngredientDto;
import codes.wokstym.cookingrecipes.service.RecipeService;
import retrofit2.Call;
import retrofit2.Response;


public class GetIngredientTask extends BackendTask<IngredientDto, RecipeService> {

    private final MainActivity responseActivity;

    public GetIngredientTask(MainActivity responseActivity) {
        this.responseActivity = responseActivity;
    }

    public void execute(UUID id) {
        callTask(RecipeService.class, service -> service.getIngredient(id));
    }


    @Override
    public void onResponse(Call<IngredientDto> call, Response<IngredientDto> response) {
        if (response.isSuccessful()) {
            IngredientDto changesList = response.body();
        } else {
            Log.d("Przepis", String.valueOf(response.errorBody()));
        }
    }

    @Override
    public void onFailure(Call<IngredientDto> call, Throwable t) {
        Log.d("Przepis", "onFailure: " + call);
        t.printStackTrace();
    }
}
