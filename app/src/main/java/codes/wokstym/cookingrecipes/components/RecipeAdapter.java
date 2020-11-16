package codes.wokstym.cookingrecipes.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import codes.wokstym.cookingrecipes.R;
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

        TextView mealTime = convertView.findViewById(R.id.recipe_meal_time);
        TextView price = convertView.findViewById(R.id.recipe_price);
        TextView title = convertView.findViewById(R.id.recipe_title);
        TextView prepTime = convertView.findViewById(R.id.recipe_prep_time);

        mealTime.setText(recipe.getMealTime().getName());
        price.setText(recipe.getPrice().toString());
        title.setText(recipe.getTitle());
        prepTime.setText(Integer.toString(recipe.getPrepTime()));

        return convertView;
    }

}
