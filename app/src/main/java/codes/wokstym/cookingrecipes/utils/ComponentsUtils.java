package codes.wokstym.cookingrecipes.utils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

import codes.wokstym.cookingrecipes.R;

public class ComponentsUtils {
    public static void toggleStatusBarColor(Activity activity, int color) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(activity.getColor(R.color.colorPrimary));
    }
}
