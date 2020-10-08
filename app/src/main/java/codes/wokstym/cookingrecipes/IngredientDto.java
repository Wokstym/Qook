package codes.wokstym.cookingrecipes;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDto {

    private UUID ID;
    private String name;
    private int amount;
    private Unit unit;
}
