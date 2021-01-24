package codes.wokstym.cookingrecipes.views

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import codes.wokstym.cookingrecipes.R
import codes.wokstym.cookingrecipes.components.IngredientAdapter
import codes.wokstym.cookingrecipes.databinding.ActivityRecipeDetailsBinding
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.utils.ExtraUtils.RECIPE_EXTRA
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class RecipeDetails : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeDto = intent.getSerializableExtra(RECIPE_EXTRA) as RecipeDto

        binding.recipeTitle.text = recipeDto.title
//        binding.recipeMealTime.text = getString(recipeDto.mealTime.nameResourceId);
        binding.recipePreparation.text = recipeDto.preparation
        binding.recipePrepTime.text = recipeDto.prepTime.toString()
        val priceString = DecimalFormat("0.00").format(recipeDto.price)
        binding.recipePrice.text = String.format("%s PLN", priceString)
        binding.ingredientListView.adapter = IngredientAdapter(this, recipeDto.ingredients)

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)

        Picasso.with(this)
                .load(recipeDto.pictureUrl)
                .placeholder(R.drawable.placeholder)
                .fit()
                .centerCrop() //optional
                .into(binding.recipeImage)


    }
}