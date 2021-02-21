package codes.wokstym.cookingrecipes.models

import androidx.annotation.StringRes
import codes.wokstym.cookingrecipes.R

enum class Unit(@StringRes val nameResourceId: Int) {
    GRAM(R.string.gram),
    SPOON(R.string.spoon),
    PIECE(R.string.piece),
    ML(R.string.ml),
    HANDFUL(R.string.handful)
}