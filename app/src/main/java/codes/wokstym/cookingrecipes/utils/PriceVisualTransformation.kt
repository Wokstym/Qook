package codes.wokstym.cookingrecipes.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class PriceVisualTransformation(val currency: String = "zł") : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        if (text.text.isBlank()) {
            return TransformedText(
                text, OffsetMapping.Identity
            )
        }
        return TransformedText(
            AnnotatedString("${text.text} $currency"), OffsetMapping.Identity
        )
    }
}

val PLNPriceVisualTransformation = PriceVisualTransformation()