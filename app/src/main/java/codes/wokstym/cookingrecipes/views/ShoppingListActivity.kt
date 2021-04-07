package codes.wokstym.cookingrecipes.views

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import codes.wokstym.cookingrecipes.components.IngredientAdapter
import codes.wokstym.cookingrecipes.components.RecipeAdapter
import codes.wokstym.cookingrecipes.databinding.ActivityShoppingListBinding
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.utils.addGridSpaceDivider
import codes.wokstym.cookingrecipes.utils.getRecipesListExtra
import codes.wokstym.cookingrecipes.utils.getShoppingListExtra
import codes.wokstym.cookingrecipes.utils.startRecipeDetailsActivity

class ShoppingListActivity : Activity() {
    private lateinit var binding: ActivityShoppingListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shoppingList = intent.getShoppingListExtra()
        val recipes = intent.getRecipesListExtra()

        binding.ingredientListView.apply {
            adapter = IngredientAdapter(this@ShoppingListActivity, shoppingList.ingredientList)
            layoutManager = LinearLayoutManager(this@ShoppingListActivity)
        }

        binding.recipeRecyclerView.apply {
            adapter = RecipeAdapter(this@ShoppingListActivity, recipes, object : RecipeAdapter.OnItemClick {
                override fun onItemClick(view: View, recipe: RecipeDto, position: Int) =
                        startRecipeDetailsActivity(recipe)

                override fun onLongPress(view: View, recipe: RecipeDto, position: Int) =
                        startRecipeDetailsActivity(recipe)
            })
            layoutManager = GridLayoutManager(this@ShoppingListActivity, 2)
            (itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
            addGridSpaceDivider(10.0)
        }

        binding.recipeListButton.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Ingredient list", shoppingList.formatClipboard(this))
            clipboard.setPrimaryClip(clip)
        }
    }
}
