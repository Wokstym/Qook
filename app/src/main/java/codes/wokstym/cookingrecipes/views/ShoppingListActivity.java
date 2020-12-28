package codes.wokstym.cookingrecipes.views;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import codes.wokstym.cookingrecipes.components.IngredientAdapter;
import codes.wokstym.cookingrecipes.databinding.ShoppinListBinding;
import codes.wokstym.cookingrecipes.models.ShoppingListDto;

import static codes.wokstym.cookingrecipes.utils.ExtraUtils.SHOPPING_LIST_EXTRA;

public class ShoppingListActivity extends Activity {

    private ShoppinListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ShoppinListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ShoppingListDto shoppingList = (ShoppingListDto) getIntent().getSerializableExtra(SHOPPING_LIST_EXTRA);

        binding.ingredientListView.setAdapter(new IngredientAdapter(this, shoppingList.getIngredientList()));

        binding.recipeListButton.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Ingredient list", shoppingList.toString());
            clipboard.setPrimaryClip(clip);
        });
    }
}
