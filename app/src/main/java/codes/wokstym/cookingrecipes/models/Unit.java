package codes.wokstym.cookingrecipes.models;

import com.fasterxml.jackson.annotation.JsonValue;

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

    @JsonValue
    private final String name;
}
