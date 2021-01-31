package codes.wokstym.cookingrecipes.components

import android.content.Context
import android.graphics.PorterDuff
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codes.wokstym.cookingrecipes.databinding.ItemRecipeBinding
import codes.wokstym.cookingrecipes.models.MealTime
import codes.wokstym.cookingrecipes.models.RecipeDto
import java.text.DecimalFormat
import java.util.*

class RecipeAdapter(
        private val context: Context,
        private val objects: List<RecipeDto>) : RecyclerView.Adapter<RecipeAdapter.NewRecipeViewHolder>() {

    private val selectedItemsPositions: SparseBooleanArray = SparseBooleanArray()
    private var onItemClick: OnItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewRecipeViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val binding: ItemRecipeBinding = ItemRecipeBinding.inflate(inflater, parent, false)
        return NewRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewRecipeViewHolder, position: Int) = holder.bindData(position)

    override fun getItemCount() = objects.size

    val selectedItems: List<RecipeDto>
        get() {
            val items: MutableList<RecipeDto> = ArrayList()
            for (i in 0 until selectedItemsPositions.size()) {
                val index = selectedItemsPositions.keyAt(i)
                val recipe = objects[index]
                items.add(recipe)
            }
            return items
        }

    fun clearSelection() {
        selectedItemsPositions.clear()
        notifyDataSetChanged()
    }

    fun toggleSelection(position: Int) {
        if (selectedItemsPositions[position]) {
            selectedItemsPositions.delete(position)
        } else {
            selectedItemsPositions.put(position, true)
        }
        notifyItemChanged(position)
    }

    fun selectedItemCount(): Int {
        return selectedItemsPositions.size()
    }

    inner class NewRecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener, OnLongClickListener {
        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        fun bindData(position: Int) {
            val recipe = objects[position]
            val mealTime = recipe.mealTime
            binding.recipeTitle.text = recipe.title
            binding.recipePrepTime.text = String.format("%s min.", recipe.prepTime)

            val priceString = DecimalFormat("0.00").format(recipe.price)
            binding.recipePrice.text = String.format("%s PLN", priceString)

            binding.recipeListItemParent.isActivated = selectedItemsPositions[position]
            setMealTimeIconData(mealTime)
            toggleIcon(position)
        }

        private fun setMealTimeIconData(mealTime: MealTime) {
            binding.mealTimeIcon.setImageResource(mealTime.iconResourceID)
            val colorID = context.getColor(mealTime.colorResourceID)
            binding.mealTimeIcon.background.setColorFilter(colorID, PorterDuff.Mode.SRC_IN)
        }

        private fun toggleIcon(position: Int) {
            if (selectedItemsPositions[position]) {
                toggleCheckMark()
            } else {
                toggleMealTimeIcon()
            }
        }

        private fun toggleMealTimeIcon() {
            binding.mealTimeIcon.visibility = View.VISIBLE
            binding.checkmark.visibility = View.GONE
        }

        private fun toggleCheckMark() {
            binding.mealTimeIcon.visibility = View.GONE
            binding.checkmark.visibility = View.VISIBLE
        }

        override fun onClick(v: View) {
            if (onItemClick != null) {
                val position = adapterPosition
                onItemClick!!.onItemClick(v, objects[position], position)
            }
        }

        override fun onLongClick(v: View): Boolean {
            if (onItemClick == null) return false
            val position = adapterPosition
            onItemClick!!.onLongPress(v, objects[position], position)
            return true
        }
    }

    interface OnItemClick {
        fun onItemClick(view: View?, recipe: RecipeDto?, position: Int)
        fun onLongPress(view: View?, recipe: RecipeDto?, position: Int)

    }

    fun setOnItemClick(onItemClick: OnItemClick?) {
        this.onItemClick = onItemClick
    }
}
