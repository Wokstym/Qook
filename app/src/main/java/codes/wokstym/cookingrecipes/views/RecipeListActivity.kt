package codes.wokstym.cookingrecipes.views

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import codes.wokstym.cookingrecipes.R
import codes.wokstym.cookingrecipes.components.RecipeAdapter
import codes.wokstym.cookingrecipes.databinding.ActivityRecipeListBinding
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.models.ShoppingListDto
import codes.wokstym.cookingrecipes.tasks.GetRecipesTask
import codes.wokstym.cookingrecipes.tasks.GetShoppingListTask
import codes.wokstym.cookingrecipes.utils.*
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }

        fun initListeners() {
            binding.recipeListButton.setOnClickListener { fetchShoppingList() }
        }

        fun setProfile() { // TODO: replace with user data
            @SuppressLint("SetTextI18n")
            binding.userName.text = "Grzegorz Poręba"
            Picasso.with(this)
                .load("https://avatars.githubusercontent.com/u/44115112?s=460&u=2fea6d808fb949060aa499dad3e3365608bb5c40&v=4")
                .into(binding.userProfile)
        }

        initSupportBar()
        setProfile()
        initListeners()
        fetchRecipes()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.add -> {
                startAddRecipeActivity()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    fun startShoppingList(shoppingList: ShoppingListDto) {
        startShoppingListActivity(shoppingList, ArrayList(recipeAdapter.selectedItems))
    }

    private fun fetchRecipes() {
        showProgressBar()
        GetRecipesTask(this).execute()
    }

    private fun fetchShoppingList() {
        val recipesUUIDs = recipeAdapter.selectedItems
            .map(RecipeDto::id)
            .toList()
        showProgressBar()
        GetShoppingListTask(this).execute(recipesUUIDs)
    }

    fun showRecipes(recipeList: List<RecipeDto>) {
        val sorted = recipeList.sorted()
        prepareAdapter(sorted)
        prepareRecyclerView()
    }

    private fun prepareAdapter(recipeList: List<RecipeDto>) {
        recipeAdapter = RecipeAdapter(this@RecipeListActivity, recipeList, object : RecipeAdapter.OnItemClick {
            override fun onItemClick(view: View, recipe: RecipeDto, position: Int) {
                if (recipeAdapter.selectedItemsCount > 0) {
                    toggleActionBar(position)
                } else {
                    startRecipeDetailsActivity(recipe)
                }
            }

            override fun onLongPress(view: View, recipe: RecipeDto, position: Int) {
                toggleActionBar(position)
            }
        })
    }

    private fun prepareRecyclerView() =
        binding.recipeRecyclerView.apply {
            adapter = recipeAdapter
            layoutManager = GridLayoutManager(this@RecipeListActivity, 2)
            (itemAnimator as DefaultItemAnimator).supportsChangeAnimations = false
            addGridSpaceDivider(10.0)
        }


    private fun toggleActionBar(position: Int) {
        if (actionMode == null) {
            actionMode = startSupportActionMode(actionCallback)
        }
        toggleSelection(position)
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

    inner class ActionCallback : ActionMode.Callback {

        override fun onCreateActionMode(mode: ActionMode, menu: Menu?): Boolean {
            binding.recipeListButton.show()
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?) = false

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?) = false

        override fun onDestroyActionMode(mode: ActionMode?) {
            recipeAdapter.clearSelection()
            actionMode = null
            binding.recipeListButton.hide()
        }
    }
}