package codes.wokstym.cookingrecipes.components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import codes.wokstym.cookingrecipes.R;
import codes.wokstym.cookingrecipes.models.MealTime;
import codes.wokstym.cookingrecipes.models.RecipeDto;

public class RecipeAdapter extends ArrayAdapter<RecipeDto> {
    public RecipeAdapter(Context context, List<RecipeDto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RecipeDto recipe = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_recipe, parent, false);
        }

        setPrice(convertView, recipe);
        setTitle(convertView, recipe);
        setPrepTime(convertView, recipe);
        setIconMealTime(convertView, recipe);
        return convertView;
    }

    private void setIconMealTime(View convertView, RecipeDto recipe) {
        ImageView mealTimeIcon = convertView.findViewById(R.id.meal_time_icon);

        MealTime mealTime = recipe.getMealTime();
        mealTimeIcon.setImageResource(mealTime.getIconResourceID());
        mealTimeIcon.getBackground().setColorFilter(getContext().getColor(mealTime.getColorResourceID()), PorterDuff.Mode.SRC_IN);

    }

    private void setPrepTime(View convertView, RecipeDto recipe) {
        TextView prepTime = convertView.findViewById(R.id.recipe_prep_time);
        prepTime.setText(Integer.toString(recipe.getPrepTime()));
    }

    private void setTitle(View convertView, RecipeDto recipe) {
        TextView title = convertView.findViewById(R.id.recipe_title);
        title.setText(recipe.getTitle());
    }

    private void setPrice(View convertView, RecipeDto recipe) {
        TextView price = convertView.findViewById(R.id.recipe_price);
        price.setText(recipe.getPrice().toString());
    }

}
