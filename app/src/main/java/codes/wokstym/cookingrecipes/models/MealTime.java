package codes.wokstym.cookingrecipes.models;

import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum MealTime {
    BREAKFAST("Śniadanie"),
    SECOND_BREAKFAST("Drugie Śniadanie"),
    DINNER("Obiad"),
    SNACK("Przekąska"),
    SUPPER("Kolacja");

    @JsonValue
    private final String name;
}
