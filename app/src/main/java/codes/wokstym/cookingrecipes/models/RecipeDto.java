package codes.wokstym.cookingrecipes.models;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto implements Serializable {

    private UUID id;
    private String preparation;
    private List<IngredientDto> ingredients;
    private MealTime mealTime;
    private int prepTime;
    private BigDecimal price;
    private String title;
}
