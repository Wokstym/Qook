package codes.wokstym.cookingrecipes.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.GridLayoutManager
import codes.wokstym.cookingrecipes.R
import codes.wokstym.cookingrecipes.components.RecipeAdapter
import codes.wokstym.cookingrecipes.databinding.ActivityRecipeListBinding
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.models.ShoppingListDto
import codes.wokstym.cookingrecipes.tasks.GetRecipesTask
import codes.wokstym.cookingrecipes.tasks.GetShoppingListTask
import codes.wokstym.cookingrecipes.utils.*

class RecipeListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeListBinding
    private var actionCallback: ActionCallback = ActionCallback()

    private lateinit var recipeAdapter: RecipeAdapter
    private var actionMode: ActionMode? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fun initSupportBar() {
            setSupportActionBar(binding.toolbar)
            supportActionBar!!.title = resources.getString(R.string.recipe_list)
        }

        fun initListeners() {
            binding.recipeListButton.setOnClickListener { fetchShoppingList() }
        }

        initSupportBar()
        initListeners()
        fetchRecipes()
    }

    private fun fetchRecipes() {
        binding.progressBar.show()
        GetRecipesTask(this).execute()
    }

    private fun fetchShoppingList() {
        val selectedItemPositions = recipeAdapter.selectedItems
        val recipesUUIDs = selectedItemPositions
                .map(RecipeDto::id)
                .toList()
        binding.progressBar.show()
        GetShoppingListTask(this).execute(recipesUUIDs)
    }

    fun showRecipes(recipeList: List<RecipeDto>) {
        val sorted = recipeList.sorted()
        prepareRecyclerView(sorted)
        prepareAdapter(sorted)
    }

    private fun prepareRecyclerView(recipeList: List<RecipeDto>) =
            binding.recipeRecyclerView.apply {
                adapter = RecipeAdapter(this@RecipeListActivity, recipeList)
                layoutManager = GridLayoutManager(this@RecipeListActivity, 2)
                addGridSpaceDivider(10.0)
            }

    private fun prepareAdapter(recipeList: List<RecipeDto>) {
        recipeAdapter = binding.recipeRecyclerView.adapter as RecipeAdapter
        recipeAdapter.onItemClick = (object : RecipeAdapter.OnItemClick {
            override fun onItemClick(view: View?, recipe: RecipeDto?, position: Int) {
                if (recipeAdapter.selectedItemsCount > 0) {
                    toggleActionBar(position)
                } else {
                    goToRecipeDetails(position, recipeList)
                }
            }

            override fun onLongPress(view: View?, recipe: RecipeDto?, position: Int) {
                toggleActionBar(position)
            }
        })
    }

    private fun toggleActionBar(position: Int) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionCallback)
        }
        toggleSelection(position)
    }

    private fun goToRecipeDetails(position: Int, recipeList: List<RecipeDto>) {
        startIntent<RecipeDetailsActivity>(Pair(RECIPE_EXTRA, recipeList[position]))
    }

    private fun toggleSelection(position: Int) {
        recipeAdapter.toggleSelection(position)
        val count = recipeAdapter.selectedItemsCount
        if (count == 0) {
            actionMode?.finish()
        } else {
            actionMode?.title = count.toString()
            actionMode?.invalidate()
        }
    }

    fun showShoppingList(shoppingList: ShoppingListDto) {
        startIntent<ShoppingListActivity>(Pair(SHOPPING_LIST_EXTRA, shoppingList))
    }

    inner class ActionCallback : ActionMode.Callback {

        override fun onCreateActionMode(mode: ActionMode, menu: Menu?): Boolean {
            toggleStatusBarColor()
            mode.menuInflater.inflate(R.menu.menu, menu)
            binding.recipeListButton.show()
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?) = false


        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?) = false


        override fun onDestroyActionMode(mode: ActionMode?) {
            recipeAdapter.clearSelection()
            actionMode = null
            binding.recipeListButton.hide()
            toggleStatusBarColor()
        }
    }

    fun hideProgress() {
        binding.progressBar.hide()
    }
}