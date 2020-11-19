package codes.wokstym.cookingrecipes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import codes.wokstym.cookingrecipes.databinding.ActivityMainBinding;
import codes.wokstym.cookingrecipes.views.RecipeListActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initComponents();
    }

    private void initComponents() {
        binding.recipeListButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            MainActivity.this.startActivity(intent);
        });
    }

}