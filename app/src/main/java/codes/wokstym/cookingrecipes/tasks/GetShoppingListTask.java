package codes.wokstym.cookingrecipes.tasks;

import android.util.Log;

import java.util.List;
import java.util.UUID;

import codes.wokstym.cookingrecipes.models.ShoppingListDto;
import codes.wokstym.cookingrecipes.service.RecipeService;
import codes.wokstym.cookingrecipes.views.RecipeListActivity;
import retrofit2.Call;
import retrofit2.Response;


public class GetShoppingListTask extends BackendTask<ShoppingListDto, RecipeService> {
    private final RecipeListActivity responseActivity;

    public GetShoppingListTask(RecipeListActivity responseActivity) {
        this.responseActivity = responseActivity;
    }

    public void execute(List<UUID> uuidsOfRecipes) {
        callTask(RecipeService.class, service -> service.getShoppingList(uuidsOfRecipes));
    }

    @Override
    public void onResponse(Call<ShoppingListDto> call, Response<ShoppingListDto> response) {
        if (response.isSuccessful()) {
            ShoppingListDto shoppingList = response.body();
            responseActivity.showShoppingList(shoppingList);
        } else {
            Log.d("Przepis", String.valueOf(response.errorBody()));
        }
        responseActivity.hideProgress();
    }

    @Override
    public void onFailure(Call<ShoppingListDto> call, Throwable t) {
        Log.d("Przepis", "onFailure: " + call);
        t.printStackTrace();
        responseActivity.hideProgress();
    }
}
