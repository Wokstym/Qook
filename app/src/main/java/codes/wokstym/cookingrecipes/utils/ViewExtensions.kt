package codes.wokstym.cookingrecipes.utils

import android.content.Context
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

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
fun View.pxToDp(dp: Float): Int = context.dpToPx(dp)

fun Context.dpToPx(dp: Float): Int =
        (dp * Resources.getSystem().displayMetrics.density).toInt()


fun Context.pxToDp(px: Float): Int =
        (px / Resources.getSystem().displayMetrics.density).toInt()
