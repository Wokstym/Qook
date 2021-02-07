package codes.wokstym.cookingrecipes.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import codes.wokstym.cookingrecipes.R
import java.io.Serializable

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

fun View.dpToPx(dp: Double): Int = context.dpToPx(dp)
fun View.pxToDp(dp: Double): Int = context.pxToDp(dp)

fun Context.dpToPx(dp: Double): Int =
        (dp * Resources.getSystem().displayMetrics.density).toInt()


fun Context.pxToDp(px: Double): Int =
        (px / Resources.getSystem().displayMetrics.density).toInt()

inline fun <reified T> Context.startIntent(vararg extras: Pair<String, Serializable>) {
    val intent = Intent(this, T::class.java)
    extras.forEach { intent.putExtra(it.first, it.second) }
    startActivity(intent)
}

fun Activity.toggleStatusBarColor(color: Int = R.color.colorPrimary) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    window.statusBarColor = getColor(color)
}
