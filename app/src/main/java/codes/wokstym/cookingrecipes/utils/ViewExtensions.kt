package codes.wokstym.cookingrecipes.utils

import android.app.Activity
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import codes.wokstym.cookingrecipes.R

fun View.setTopPadding(value: Int) {
    setPadding(paddingLeft,
            value,
            paddingRight,
            paddingBottom
    )
}

fun View.setTopMargin(top: Int) {

    val param = layoutParams as ViewGroup.MarginLayoutParams

    param.setMargins(
            param.leftMargin,
            top,
            param.rightMargin,
            param.bottomMargin
    )
}

fun dpToPx(dp: Double): Int =
        (dp * Resources.getSystem().displayMetrics.density).toInt()

fun pxToDp(px: Double): Int =
        (px / Resources.getSystem().displayMetrics.density).toInt()

fun Activity.toggleStatusBarColor(color: Int = R.color.colorPrimary) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = getColor(color)
}