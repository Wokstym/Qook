package codes.wokstym.cookingrecipes.views;

import android.app.Activity;
import android.os.Bundle;

import codes.wokstym.cookingrecipes.components.IngredientAdapter;
import codes.wokstym.cookingrecipes.databinding.RecipeDetailsBinding;
import codes.wokstym.cookingrecipes.models.RecipeDto;

import static codes.wokstym.cookingrecipes.utils.ExtraUtils.RECIPE_EXTRA;

public class RecipeDetails extends Activity {

    private RecipeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RecipeDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        RecipeDto recipeDto = (RecipeDto) getIntent().getSerializableExtra(RECIPE_EXTRA);

        binding.recipeTitle.setText(recipeDto.getTitle());
        binding.recipeMealTime.setText(recipeDto.getMealTime().getName());
        binding.recipePreparation.setText(recipeDto.getPreparation());
        binding.recipePrepTime.setText(Integer.toString(recipeDto.getPrepTime()));
        binding.recipePrice.setText(recipeDto.getPrice().toString());
        binding.ingredientListView.setAdapter(new IngredientAdapter(this, recipeDto.getIngredients()));


    }
}
