package codes.wokstym.cookingrecipes.models;

import com.fasterxml.jackson.annotation.JsonValue;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import codes.wokstym.cookingrecipes.R;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum MealTime {
    BREAKFAST("Śniadanie", 1, R.drawable.ic_coffee, R.color.meal_breakfast),
    SECOND_BREAKFAST("Drugie Śniadanie", 2, R.drawable.ic_stroopwafel, R.color.meal_second),
    DINNER("Obiad", 3, R.drawable.ic_drumstick_bite, R.color.meal_dinner),
    SNACK("Przekąska", 4, R.drawable.ic_apple_alt, R.color.meal_snack),
    SUPPER("Kolacja", 5, R.drawable.ic_wine_glass, R.color.meal_supper);

    @JsonValue
    private final String name;
    private int order;
    @DrawableRes
    int iconResourceID;
    @ColorRes
    int colorResourceID;
}
