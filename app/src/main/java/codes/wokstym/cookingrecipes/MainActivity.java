package codes.wokstym.cookingrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Collections;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import codes.wokstym.cookingrecipes.components.RecipeAdapter;
import codes.wokstym.cookingrecipes.databinding.ActivityMainBinding;
import codes.wokstym.cookingrecipes.models.RecipeDto;
import codes.wokstym.cookingrecipes.tasks.GetRecipesTask;
import codes.wokstym.cookingrecipes.views.RecipeDetails;

import static codes.wokstym.cookingrecipes.utils.ExtraUtils.RECIPE_EXTRA;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
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
            Intent intent = new Intent(MainActivity.this, RecipeDetails.class);
            intent.putExtra(RECIPE_EXTRA, changesList.get(position));
            MainActivity.this.startActivity(intent);
        });
    }

    public void hideProgress() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }
}