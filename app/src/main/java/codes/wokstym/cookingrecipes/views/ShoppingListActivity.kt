package codes.wokstym.cookingrecipes.views

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import codes.wokstym.cookingrecipes.components.IngredientAdapter
import codes.wokstym.cookingrecipes.databinding.ShoppinListBinding
import codes.wokstym.cookingrecipes.models.ShoppingListDto
import codes.wokstym.cookingrecipes.utils.SHOPPING_LIST_EXTRA

class ShoppingListActivity : Activity() {
    private lateinit var binding: ShoppinListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ShoppinListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val shoppingList = intent.getSerializableExtra(SHOPPING_LIST_EXTRA) as ShoppingListDto

        binding.ingredientListView.apply {
            adapter = IngredientAdapter(shoppingList.ingredientList)
            layoutManager = LinearLayoutManager(this@ShoppingListActivity)
        }

        binding.recipeListButton.setOnClickListener {
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Ingredient list", shoppingList.toString())
            clipboard.setPrimaryClip(clip)
        }
    }
}
