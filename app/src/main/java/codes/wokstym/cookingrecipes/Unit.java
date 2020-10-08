package codes.wokstym.cookingrecipes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum Unit {
    GRAM("gram"),
    SPOON("łyżek"),
    PIECE("sztuk"),
    ML("ml"),
    HANDFUL("garść")
    ;


    private final String name;
}
