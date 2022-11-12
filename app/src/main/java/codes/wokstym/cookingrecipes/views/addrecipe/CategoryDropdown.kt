package codes.wokstym.cookingrecipes.views.addrecipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.toSize

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryDropdown(
    items: List<String>,
    onSelect: (String) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }
    var selectedIndex by remember { mutableStateOf(-1) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    val focusRequester = remember { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.TopStart)
    ) {
        OutlinedTextField(
            readOnly = true,
            value = if (selectedIndex == -1) "" else items[selectedIndex],
            label = {
                Text(
                    text = "Meal time",
                    style = TextStyle(fontWeight = FontWeight.Bold)
                )
            },
            modifier = Modifier
                .focusRequester(focusRequester)
                .onGloballyPositioned { coordinates ->
                    //This value is used to assign to the DropDown the same width
                    textFieldSize = coordinates.size.toSize()
                },
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = expanded
                ) {
                    focusRequester.requestFocus()
                    expanded = true
                }
            }
        )
        DropdownMenu(expanded = expanded,
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.width.toDp() }),
            onDismissRequest = { expanded = false }) {
            items.forEachIndexed { index, s ->

                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                    onSelect(items[selectedIndex])
                }) {
                    Text(text = s)
                }
            }
        }
    }
}