package codes.wokstym.cookingrecipes.utils

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup

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