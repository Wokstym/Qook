package codes.wokstym.cookingrecipes.tasks;

import android.util.Log;

import codes.wokstym.cookingrecipes.MainActivity;
import codes.wokstym.cookingrecipes.models.IngredientDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetIngredientTask implements Callback<IngredientDto> {

    private MainActivity responseActivity;

    public GetIngredientTask(MainActivity responseActivity) {
        this.responseActivity = responseActivity;
    }


    @Override
    public void onResponse(Call<IngredientDto> call, Response<IngredientDto> response) {
        IngredientDto changesList;
        if (response.isSuccessful()) {
            changesList = response.body();
        } else {
            Log.d("Przepis", String.valueOf(response.errorBody()));
            changesList = new IngredientDto();
        }
        responseActivity.showIngredients(changesList);
    }

    @Override
    public void onFailure(Call<IngredientDto> call, Throwable t) {
        Log.d("Przepis", "onFailure: " + call);
        t.printStackTrace();
    }
}
