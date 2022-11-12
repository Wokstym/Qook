package codes.wokstym.cookingrecipes.components

import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codes.wokstym.cookingrecipes.databinding.ItemRecipeBinding
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.utils.dpToPx
import codes.wokstym.cookingrecipes.utils.recipe
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class RecipeAdapter(
        private val context: Context,
        private val objects: List<RecipeDto>,
        var onItemClick: OnItemClick? = null) : RecyclerView.Adapter<RecipeAdapter.NewRecipeViewHolder>() {

    private val selectedItemsPositions: SparseBooleanArray = SparseBooleanArray()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewRecipeViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val binding: ItemRecipeBinding = ItemRecipeBinding.inflate(inflater, parent, false)
        return NewRecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewRecipeViewHolder, position: Int) = holder.bindData(position)

    override fun getItemCount() = objects.size

    override fun getItemId(position: Int): Long {
        val product: RecipeDto = objects[position]
        return product.id.mostSignificantBits and Long.MAX_VALUE
    }

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

    val selectedItemsCount: Int
        get() = selectedItemsPositions.size()

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

    inner class NewRecipeViewHolder(private val binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener, OnLongClickListener {
        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        fun bindData(position: Int) {
            val recipe = objects[position]
            binding.title.text = recipe.title
            binding.details.prepTime.text = String.format("%s min.", recipe.prepTime)
            binding.mealTime.text = context.getString(recipe.mealTime.nameResourceId)

            val priceString = DecimalFormat("0.00").format(recipe.price)
            binding.details.price.text = String.format("%s PLN", priceString)

            setMealPicture(recipe.pictureUrl)
            toggleBorder(position)
        }

        private fun setMealPicture(url: String?) {
            /* don't reload picture if already set */
            if (binding.picture.drawable == null)
                Picasso.with(context)
                    .load(url)
                    .recipe()
                    .into(binding.picture)
        }

        private fun toggleBorder(position: Int) {
            if (selectedItemsPositions[position]) {
                toggleVisibleBorder()
            } else {
                toggleNoBorder()
            }
        }

        private fun toggleNoBorder() {
            binding.pictureCardView.strokeWidth = 0
        }

        private fun toggleVisibleBorder() {
            binding.pictureCardView.strokeWidth = dpToPx(3.0)
        }

        override fun onClick(v: View) {
            onItemClick?.onItemClick(v, objects[adapterPosition], adapterPosition)
        }

        override fun onLongClick(v: View): Boolean {
            onItemClick?.onLongPress(v, objects[adapterPosition], adapterPosition)
            return onItemClick != null
        }
    }

    interface OnItemClick {
        fun onItemClick(view: View, recipe: RecipeDto, position: Int)
        fun onLongPress(view: View, recipe: RecipeDto, position: Int)
    }
}
