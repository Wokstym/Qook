package codes.wokstym.cookingrecipes.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import codes.wokstym.cookingrecipes.R;
import codes.wokstym.cookingrecipes.components.RecipeAdapter;
import codes.wokstym.cookingrecipes.components.VerticalSpaceItemDecoration;
import codes.wokstym.cookingrecipes.databinding.ActivityRecipeListBinding;
import codes.wokstym.cookingrecipes.models.RecipeDto;
import codes.wokstym.cookingrecipes.models.ShoppingListDto;
import codes.wokstym.cookingrecipes.tasks.GetRecipesTask;
import codes.wokstym.cookingrecipes.tasks.GetShoppingListTask;
import codes.wokstym.cookingrecipes.utils.ComponentsUtils;

import static codes.wokstym.cookingrecipes.utils.ConversionUtils.dpToPx;
import static codes.wokstym.cookingrecipes.utils.ExtraUtils.RECIPE_EXTRA;
import static codes.wokstym.cookingrecipes.utils.ExtraUtils.SHOPPING_LIST_EXTRA;

public class RecipeListActivity extends AppCompatActivity {

    private RecipeAdapter recipeAdapter;
    private ActivityRecipeListBinding binding;
    List<RecipeDto> recipeList;
    ActionMode actionMode;
    ActionCallback actionCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecipeListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        actionCallback = new ActionCallback();

        initSupportBar();
        initListeners();

        fetchRecipes();
    }

    private void initSupportBar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(getResources().getString(R.string.recipe_list));
    }

    private void initListeners() {
        binding.recipeListButton.setOnClickListener(v -> fetchShoppingList());
    }

    private void fetchRecipes() {
        showProgress();
        new GetRecipesTask(this).execute();
    }

    public void showRecipes(List<RecipeDto> recipeList) {
        this.recipeList = recipeList;
        sortRecipeList(recipeList);

        prepareAdapter(recipeList);
        prepareRecipeRecyclerView();
    }

    private void sortRecipeList(List<RecipeDto> changesList) {
        changesList.sort((o1, o2) -> o1.getMealTime().getOrder() - o2.getMealTime().getOrder());
    }

    private void prepareAdapter(List<RecipeDto> recipeList) {
        recipeAdapter = new RecipeAdapter(this, recipeList);
        recipeAdapter.setOnItemClick(new RecipeAdapter.OnItemClick() {

            @Override
            public void onItemClick(View view, RecipeDto recipe, int position) {
                if (recipeAdapter.selectedItemCount() > 0) {
                    toggleActionBar(position);
                } else {
                    goToRecipeDetails(position, recipeList);
                }
            }

            @Override
            public void onLongPress(View view, RecipeDto recipe, int position) {
                toggleActionBar(position);
            }
        });
    }

    private void prepareRecipeRecyclerView() {
        binding.recipeRecyclerView.setAdapter(recipeAdapter);
        binding.recipeRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        VerticalSpaceItemDecoration dividerItemDecoration = new VerticalSpaceItemDecoration(dpToPx(5));
        binding.recipeRecyclerView.addItemDecoration(dividerItemDecoration);
    }

    private void toggleActionBar(int position) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionCallback);
        }
        toggleSelection(position);
    }

    private void goToRecipeDetails(int position, List<RecipeDto> recipeList) {
        Intent intent = new Intent(this, RecipeDetails.class);
        intent.putExtra(RECIPE_EXTRA, recipeList.get(position));
        startActivity(intent);
    }

    private void toggleSelection(int position) {
        recipeAdapter.toggleSelection(position);
        int count = recipeAdapter.selectedItemCount();
        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    private void fetchShoppingList() {
        List<RecipeDto> selectedItemPositions = recipeAdapter.getSelectedItems();
        List<UUID> recipesUUIDs = selectedItemPositions.stream()
                .map(RecipeDto::getId)
                .collect(Collectors.toList());

        showProgress();
        new GetShoppingListTask(this).execute(recipesUUIDs);
    }

    public void hideProgress() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void showProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    public void showShoppingList(ShoppingListDto shoppingList) {
        Intent intent = new Intent(this, ShoppingListActivity.class);
        intent.putExtra(SHOPPING_LIST_EXTRA, shoppingList);
        startActivity(intent);
    }

    private class ActionCallback implements ActionMode.Callback {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            ComponentsUtils.toggleStatusBarColor(RecipeListActivity.this, R.color.DarkSlateGray);
            mode.getMenuInflater().inflate(R.menu.menu, menu);
            binding.recipeListButton.setVisibility(View.VISIBLE);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            recipeAdapter.clearSelection();
            actionMode = null;
            binding.recipeListButton.setVisibility(View.GONE);
            ComponentsUtils.toggleStatusBarColor(RecipeListActivity.this, R.color.colorPrimary);
        }
    }

}
