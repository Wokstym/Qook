package codes.wokstym.cookingrecipes.components

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import codes.wokstym.cookingrecipes.R
import codes.wokstym.cookingrecipes.databinding.ItemPreparationStepBinding

class StepsAdapter(private val objects: List<String>) : RecyclerView.Adapter<StepsAdapter.StepsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemPreparationStepBinding = ItemPreparationStepBinding.inflate(inflater, parent, false)
        return StepsViewHolder(binding, parent.context)
    }

    override fun getItemCount(): Int = objects.size

    override fun onBindViewHolder(holder: StepsViewHolder, position: Int) = holder.bind(objects[position], position)

    inner class StepsViewHolder(
            private val binding: ItemPreparationStepBinding,
            private val context: Context) : RecyclerView.ViewHolder(binding.root) {

        fun bind(stepContent: String, position: Int) {
            binding.step.text = String.format(context.getString(R.string.step_label), position + 1)
            binding.content.text = stepContent
        }
    }
}