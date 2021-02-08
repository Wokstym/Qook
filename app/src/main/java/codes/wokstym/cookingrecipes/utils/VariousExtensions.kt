package codes.wokstym.cookingrecipes.utils

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import codes.wokstym.cookingrecipes.R
import codes.wokstym.cookingrecipes.components.GridSpaceItemDecoration
import codes.wokstym.cookingrecipes.components.VerticalSpaceItemDecoration
import com.squareup.picasso.RequestCreator

fun RequestCreator.recipe(): RequestCreator {
    return placeholder(R.drawable.placeholder)
            .fit()
            .centerCrop()
}

fun RecyclerView.addVerticalSpaceDivider(dpSize: Double) {
    val dividerItemDecoration = VerticalSpaceItemDecoration(dpToPx(dpSize))
    addItemDecoration(dividerItemDecoration)
}

fun RecyclerView.addGridSpaceDivider(dpSize: Double) {
    val manager: GridLayoutManager = layoutManager as GridLayoutManager
    val dividerItemDecoration = GridSpaceItemDecoration(manager.spanCount, dpToPx(dpSize))
    addItemDecoration(dividerItemDecoration)
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}