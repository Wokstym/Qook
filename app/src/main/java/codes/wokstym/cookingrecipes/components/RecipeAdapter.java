package codes.wokstym.cookingrecipes.components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import codes.wokstym.cookingrecipes.databinding.ItemRecipeBinding;
import codes.wokstym.cookingrecipes.models.MealTime;
import codes.wokstym.cookingrecipes.models.RecipeDto;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    final static int ITEM_NOT_SELECTED = -1;

    private Context context;
    private List<RecipeDto> objects;
    private LayoutInflater inflater;
    private SparseBooleanArray selectedItemsPositions;
    private int selectedIndex = ITEM_NOT_SELECTED;

    private OnItemClick onItemClick;

    public RecipeAdapter(Context context, List<RecipeDto> objects) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.objects = objects;
        selectedItemsPositions = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRecipeBinding binding = ItemRecipeBinding.inflate(inflater, parent, false);
        return new RecipeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public List<RecipeDto> getSelectedItems() {
        List<RecipeDto> items = new ArrayList<>();
        for (int i = 0; i < selectedItemsPositions.size(); i++) {
            int index = selectedItemsPositions.keyAt(i);
            RecipeDto recipe = objects.get(index);
            items.add(recipe);
        }

        return items;
    }

    public void clearSelection() {
        selectedItemsPositions.clear();
        notifyDataSetChanged();
    }

    public void toggleSelection(int position) {
        selectedIndex = position;
        if (selectedItemsPositions.get(position)) {
            selectedItemsPositions.delete(position);
        } else {
            selectedItemsPositions.put(position, true);
        }
        notifyItemChanged(position);
    }

    public int selectedItemCount() {
        return selectedItemsPositions.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        ItemRecipeBinding binding;

        public RecipeViewHolder(ItemRecipeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bindData(int position) {
            RecipeDto recipe = objects.get(position);
            MealTime mealTime = recipe.getMealTime();

            binding.recipePrepTime.setText(Integer.toString(recipe.getPrepTime()));
            binding.recipeTitle.setText(recipe.getTitle());
            binding.recipePrice.setText(recipe.getPrice().toString());
            binding.recipeListItemParent.setActivated(selectedItemsPositions.get(position));

            setMealTimeIconData(mealTime);
            toggleIcon(position);
        }

        public void setMealTimeIconData(MealTime mealTime) {
            binding.mealTimeIcon.setImageResource(mealTime.getIconResourceID());

            int colorID = context.getColor(mealTime.getColorResourceID());
            binding.mealTimeIcon.getBackground().setColorFilter(colorID, PorterDuff.Mode.SRC_IN);
        }

        private void toggleIcon(int position) {
            if (selectedItemsPositions.get(position)) {
                toggleCheckMark();
            } else {
                toggleMealTimeIcon();
            }
            if (selectedIndex == position)
                selectedIndex = ITEM_NOT_SELECTED;
        }

        private void toggleMealTimeIcon() {
            binding.mealTimeIcon.setVisibility(View.VISIBLE);
            binding.checkmark.setVisibility(View.GONE);
        }

        private void toggleCheckMark() {
            binding.mealTimeIcon.setVisibility(View.GONE);
            binding.checkmark.setVisibility(View.VISIBLE);
        }


        @Override
        public void onClick(View v) {
            if (onItemClick != null) {
                int position = getAdapterPosition();
                onItemClick.onItemClick(v, objects.get(position), position);
            }
        }

        @Override
        public boolean onLongClick(View v) {
            if (onItemClick == null)
                return false;

            int position = getAdapterPosition();
            onItemClick.onLongPress(v, objects.get(position), position);
            return true;
        }
    }

    public interface OnItemClick {
        void onItemClick(View view, RecipeDto recipe, int position);

        void onLongPress(View view, RecipeDto recipe, int position);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }
}
