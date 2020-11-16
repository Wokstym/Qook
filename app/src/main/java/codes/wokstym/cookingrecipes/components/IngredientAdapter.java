package codes.wokstym.cookingrecipes.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import codes.wokstym.cookingrecipes.R;
import codes.wokstym.cookingrecipes.models.IngredientDto;

public class IngredientAdapter extends ArrayAdapter<IngredientDto> {
    public IngredientAdapter(Context context, List<IngredientDto> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        IngredientDto ingredient = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ingredient, parent, false);
        }

        TextView name = convertView.findViewById(R.id.ingredient_name);
        TextView amount = convertView.findViewById(R.id.ingredient_amount);
        TextView unit = convertView.findViewById(R.id.ingredient_unit);


        name.setText(ingredient.getName());
        amount.setText(Integer.toString(ingredient.getAmount()));
        unit.setText(ingredient.getUnit().getName());

        return convertView;
    }

}
