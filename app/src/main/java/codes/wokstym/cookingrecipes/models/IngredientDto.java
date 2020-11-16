package codes.wokstym.cookingrecipes.models;

import java.io.Serializable;
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
public class IngredientDto implements Serializable {

    private UUID id;
    private String name;
    private Unit unit;
    private int amount;
}
