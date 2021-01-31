package codes.wokstym.cookingrecipes.views

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.marginTop
import androidx.recyclerview.widget.LinearLayoutManager
import codes.wokstym.cookingrecipes.components.IngredientAdapter
import codes.wokstym.cookingrecipes.components.StepsAdapter
import codes.wokstym.cookingrecipes.databinding.ActivityRecipeDetailsBinding
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.utils.ExtraUtils.RECIPE_EXTRA
import codes.wokstym.cookingrecipes.utils.addVerticalSpaceDivider
import codes.wokstym.cookingrecipes.utils.recipe
import codes.wokstym.cookingrecipes.utils.setTopMargin
import codes.wokstym.cookingrecipes.utils.setTopPadding
import com.google.android.material.appbar.AppBarLayout
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class RecipeDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeDetailsBinding
    private lateinit var recipeDto: RecipeDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipeDto = intent.getSerializableExtra(RECIPE_EXTRA) as RecipeDto

        fun bindViews() {
            fun bindIngredientsList() = binding.ingredientListView.apply {
                adapter = IngredientAdapter(recipeDto.ingredients)
                layoutManager = LinearLayoutManager(this@RecipeDetailsActivity)
                addVerticalSpaceDivider(5)
            }

            fun bindPreparationSteps() = binding.ingredientPreparationSteps.apply {
                adapter = StepsAdapter(recipeDto.getPreparations())
                layoutManager = LinearLayoutManager(this@RecipeDetailsActivity)
                addVerticalSpaceDivider(10)
            }
            binding.recipeTitle.text = recipeDto.title
            binding.recipePrepTime.text = String.format("%s min.", recipeDto.prepTime)

            val priceString = DecimalFormat("0.00").format(recipeDto.price)
            binding.recipePrice.text = String.format("%s PLN", priceString)


            bindIngredientsList()
            bindPreparationSteps()


            Picasso.with(this)
                    .load(recipeDto.pictureUrl)
                    .recipe()
                    .into(binding.recipeImage)
        }
        bindViews()
        prepareHeaderAnimation()
    }

    private fun prepareHeaderAnimation() {
        val helper = HeaderAnimationHelper(
                viewBeingTransformed = binding.header,
                appBar = binding.appBarLayout,
                contentView = findViewById<View>(android.R.id.content))

        binding.appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset -> helper.transform(verticalOffset) })
    }

    class HeaderAnimationHelper(private val viewBeingTransformed: View, appBar: AppBarLayout, contentView: View) {

        private val baseTopMargin = viewBeingTransformed.marginTop
        private val baseTopPadding = viewBeingTransformed.paddingTop
        private val backgroundDrawable = viewBeingTransformed.background as GradientDrawable

        private val cornerRadii = backgroundDrawable.cornerRadii!!
        private val roundedCornerXRadius = cornerRadii[0]
        private val roundedCornerYRadius = cornerRadii[1]

        private var areaObscuredByNotificationBar = 0
        private val appBarTotalScrollRange: Float by lazy {
            appBar.totalScrollRange.toFloat()
        }

        init {
            ViewCompat.setOnApplyWindowInsetsListener(contentView) { _, insets ->
                areaObscuredByNotificationBar = insets.systemWindowInsetTop
                insets
            }
            backgroundDrawable.mutate() // required so this background isn't reused with bad radius state
        }

        fun transform(verticalOffset: Int) {
            val percentScrolled: Float = calculateScrolledPercent(verticalOffset)
            val newTopPadding = calculateTopPadding(percentScrolled)

            if (newTopPadding != viewBeingTransformed.paddingTop) {
                viewBeingTransformed.setTopPadding(newTopPadding)
                setTopMargin(percentScrolled)
                setDrawableRadius(percentScrolled)
            }
        }

        private fun calculateScrolledPercent(verticalOffset: Int): Float =
                (-verticalOffset / appBarTotalScrollRange)

        private fun calculateTopPadding(percentScrolled: Float): Int {
            val paddingToAdd = areaObscuredByNotificationBar * percentScrolled
            return baseTopPadding + paddingToAdd.toInt()
        }

        private fun setTopMargin(percentScrolled: Float) {
            val marginShrinkage = calculateMargiShrinkPercent(percentScrolled)
            val newTopMargin = (marginShrinkage * baseTopMargin).toInt()
            viewBeingTransformed.setTopMargin(newTopMargin)
        }

        private fun setDrawableRadius(percentScrolled: Float) {
            val radiusShrinkage = calculateRadiusShrinkPercent(percentScrolled)
            setTopLeftRadius(radiusShrinkage)
            setTopRightRadius(radiusShrinkage)
            backgroundDrawable.cornerRadii = cornerRadii
        }

        private fun calculateMargiShrinkPercent(percentScrolled: Float): Float {
            return calculateShrinkPercent(percentScrolled, MARGIN_SHRINK_RANGE)
        }

        private fun calculateRadiusShrinkPercent(percentScrolled: Float): Float {
            return calculateShrinkPercent(percentScrolled, CORNER_SHRINK_RANGE)
        }

        private fun setTopLeftRadius(radiusShrinkage: Float) {
            cornerRadii[0] = roundedCornerXRadius * radiusShrinkage
            cornerRadii[1] = roundedCornerYRadius * radiusShrinkage
        }

        private fun setTopRightRadius(radiusShrinkage: Float) {
            cornerRadii[2] = roundedCornerXRadius * radiusShrinkage
            cornerRadii[3] = roundedCornerYRadius * radiusShrinkage
        }

        private fun calculateShrinkPercent(percentScrolled: Float, shrinkRange: Float): Float {
            val shouldChangeRadius = percentScrolled > (1.0f - shrinkRange)
            return if (shouldChangeRadius) {
                (1.0f - percentScrolled) / shrinkRange
            } else {
                1.0f
            }
        }

        companion object {
            const val CORNER_SHRINK_RANGE = 0.15f
            const val MARGIN_SHRINK_RANGE = 0.15f
        }

    }
}





