package codes.wokstym.cookingrecipes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import androidx.appcompat.app.AppCompatActivity;
import codes.wokstym.cookingrecipes.components.RecipeAdapter;
import codes.wokstym.cookingrecipes.databinding.ActivityMainBinding;
import codes.wokstym.cookingrecipes.models.IngredientDto;
import codes.wokstym.cookingrecipes.models.RecipeDto;
import codes.wokstym.cookingrecipes.service.RecipeServiceImpl;
import codes.wokstym.cookingrecipes.tasks.GetIngredientTask;
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

        try {
            new RecipeServiceImpl().getIngredient(UUID.fromString("09189ba1-de8a-4226-bb35-ab1a97cef800"), new GetIngredientTask(this));
            new RecipeServiceImpl().getRecipes(new GetRecipesTask(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showIngredients(IngredientDto changesList) {
        Log.d("PrzepisTag", "showRecipes: " + changesList);
//        binding.ingredientListView.setAdapter(new IngredientAdapter(this, Collections.singletonList(changesList)));
    }

    public void showRecipes(List<RecipeDto> changesList) {
        Log.d("PrzepisTag", "showRecipes: " + changesList);

        Collections.sort(changesList, (o1, o2) -> o1.getMealTime().compareTo(o2.getMealTime()));

        binding.recipeListView.setAdapter(new RecipeAdapter(this, changesList));
        binding.recipeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, RecipeDetails.class);
                intent.putExtra(RECIPE_EXTRA, changesList.get(position));
                MainActivity.this.startActivity(intent);
            }
        });
    }
}