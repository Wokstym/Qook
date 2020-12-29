package codes.wokstym.cookingrecipes.models;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import codes.wokstym.cookingrecipes.R;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum MealTime {
    BREAKFAST(R.string.breakfast_name, 1, R.drawable.ic_coffee, R.color.meal_breakfast),
    SECOND_BREAKFAST(R.string.second_breakfast_name, 2, R.drawable.ic_stroopwafel, R.color.meal_second),
    DINNER(R.string.dinner_name, 3, R.drawable.ic_drumstick_bite, R.color.meal_dinner),
    SNACK(R.string.snack_name, 4, R.drawable.ic_apple_alt, R.color.meal_snack),
    SUPPER(R.string.supper_name, 5, R.drawable.ic_wine_glass, R.color.meal_supper);

    @StringRes
    private final int nameResourceId;

    private final int order;

    @DrawableRes
    private final int iconResourceID;

    @ColorRes
    private final int colorResourceID;
}
