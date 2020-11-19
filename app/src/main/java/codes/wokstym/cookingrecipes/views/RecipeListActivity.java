package codes.wokstym.cookingrecipes.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Collections;
import java.util.List;

import codes.wokstym.cookingrecipes.components.RecipeAdapter;
import codes.wokstym.cookingrecipes.databinding.ActivityRecipeListBinding;
import codes.wokstym.cookingrecipes.models.RecipeDto;
import codes.wokstym.cookingrecipes.tasks.GetRecipesTask;

import static codes.wokstym.cookingrecipes.utils.ExtraUtils.RECIPE_EXTRA;

public class RecipeListActivity extends Activity {

    ActivityRecipeListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fetchRecipes();
    }

    private void fetchRecipes() {
        showProgress();
        new GetRecipesTask(this).execute();
    }

    public void showRecipes(List<RecipeDto> changesList) {

        Collections.sort(changesList, (o1, o2) -> o1.getMealTime().compareTo(o2.getMealTime()));

        binding.recipeListView.setAdapter(new RecipeAdapter(this, changesList));
        binding.recipeListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(RecipeListActivity.this, RecipeDetails.class);
            intent.putExtra(RECIPE_EXTRA, changesList.get(position));
            RecipeListActivity.this.startActivity(intent);
        });
    }

    public void hideProgress() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

}
