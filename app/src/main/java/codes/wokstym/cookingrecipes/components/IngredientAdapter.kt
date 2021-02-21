package codes.wokstym.cookingrecipes.components

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codes.wokstym.cookingrecipes.databinding.ItemIngredientBinding
import codes.wokstym.cookingrecipes.models.IngredientDto

class IngredientAdapter(
        private val context: Context,
        private val objects: List<IngredientDto>) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemIngredientBinding = ItemIngredientBinding.inflate(inflater, parent, false)
        return IngredientViewHolder(binding)
    }

    override fun getItemCount(): Int = objects.size

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) = holder.bind(objects[position])

    inner class IngredientViewHolder(private val binding: ItemIngredientBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingredient: IngredientDto) {
            binding.ingredientAmount.text = ingredient.amount.toString()
            binding.ingredientName.text = ingredient.name
            binding.ingredientUnit.text = context.getString(ingredient.unit.nameResourceId)
        }
    }
}