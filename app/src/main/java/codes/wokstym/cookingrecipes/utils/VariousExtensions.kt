package codes.wokstym.cookingrecipes.utils

import androidx.recyclerview.widget.RecyclerView
import codes.wokstym.cookingrecipes.R
import codes.wokstym.cookingrecipes.components.VerticalSpaceItemDecoration
import com.squareup.picasso.RequestCreator

fun RequestCreator.recipe(): RequestCreator {
    return placeholder(R.drawable.placeholder)
            .fit()
            .centerCrop() //option
}

fun RecyclerView.addVerticalSpaceDivider(dpSize: Int) {
    val dividerItemDecoration = VerticalSpaceItemDecoration(ConversionUtils.dpToPx(dpSize))
    addItemDecoration(dividerItemDecoration)
}
