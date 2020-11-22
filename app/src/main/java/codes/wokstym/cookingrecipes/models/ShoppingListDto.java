package codes.wokstym.cookingrecipes.models;

import java.io.Serializable;
import java.util.List;

import androidx.annotation.NonNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingListDto implements Serializable {
    private List<IngredientDto> ingredientList;

    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (IngredientDto ingredientQuantity : ingredientList) {
            result.append(ingredientQuantity.getName())
                    .append(" - ")
                    .append(ingredientQuantity.getAmount())
                    .append(" ")
                    .append(ingredientQuantity.getUnit().getName())
                    .append(" \n");
        }

        return result.toString();
    }
}
