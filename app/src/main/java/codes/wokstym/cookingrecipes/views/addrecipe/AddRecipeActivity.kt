package codes.wokstym.cookingrecipes.views.addrecipe

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import codes.wokstym.cookingrecipes.databinding.ActivityAddRecipeBinding
import codes.wokstym.cookingrecipes.models.RecipeDto
import codes.wokstym.cookingrecipes.service.NewRecipeDto
import codes.wokstym.cookingrecipes.service.RecipeService
import codes.wokstym.cookingrecipes.tasks.createService
import codes.wokstym.cookingrecipes.utils.PLNPriceVisualTransformation
import coil.compose.AsyncImage
import com.google.android.material.composethemeadapter.MdcTheme
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val EASY_PRICE_REGEX = """\d{0,9}\.?\d?\d?""".toRegex()

val STRICT_PRICE_REGEX = """\d{1,9}(\.\d{2})?""".toRegex()

class AddRecipeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.main.setContent {
            MdcTheme {
                AddRecipe()
            }
        }
    }

    @Composable
    @Preview
    private fun AddRecipe() {
        val context = LocalContext.current

        var mealTimes by remember { mutableStateOf(listOf<String>()) }
        var title by remember { mutableStateOf("") }
        var preparation by remember { mutableStateOf("") }
        var prepTime by remember { mutableStateOf<Int?>(null) }
        var mealTime by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("0.00") }
        var imageUri by remember { mutableStateOf<Uri?>(null) }

        LaunchedEffect(true) {
            val recipeService = createService(RecipeService::class.java)
            mealTimes = recipeService.getMealTimes()
        }

        val pickPictureLauncher = rememberLauncherForActivityResult(
            ActivityResultContracts.OpenDocument()
        ) { resultUri: Uri? ->
            if (resultUri != null) {
                imageUri = resultUri
            }
        }


        fun isSendEnabled(): Boolean {
            return title.isNotBlank() &&
                    preparation.isNotBlank() &&
                    prepTime != null &&
                    price.matches(STRICT_PRICE_REGEX) &&
                    mealTime.isNotBlank()
        }

        val onClick: () -> Unit = {
            val newRecipe = NewRecipeDto(
                title = title,
                preparation = preparation,
                prepTime = prepTime!!,
                price = price.replace(".", "").toBigDecimal(),
                mealTime = mealTime,
                ingredients = listOf()
            )

            val item = context.contentResolver.openInputStream(imageUri!!)
            val bytes = item?.readBytes()
            println(bytes)
            item?.close()


            val filePart = bytes?.let {
                MultipartBody.Part.createFormData(
                    "image", "xd", RequestBody.create("image/*".toMediaTypeOrNull(), it)
                )
            }


            val recipeService = createService(RecipeService::class.java)
            recipeService.addRecipe(
                newRecipe, filePart
            ).enqueue(object : Callback<RecipeDto> {
                override fun onResponse(call: Call<RecipeDto>, response: Response<RecipeDto>) {
                    Toast.makeText(this@AddRecipeActivity, response.message(), Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onFailure(call: Call<RecipeDto>, t: Throwable) {
                    Toast.makeText(this@AddRecipeActivity, t.toString(), Toast.LENGTH_SHORT).show()
                }

            })


        }
        Surface {
            Scaffold(content = { padding ->
                Column(
                    modifier = Modifier
                        .padding(padding)
                ) {
                    OutlinedTextField(value = title, label = {
                        Text(
                            text = "Title", style = TextStyle(fontWeight = FontWeight.Bold)
                        )
                    }, onValueChange = {
                        title = it
                    }, placeholder = { Text(text = "Enter name") }, modifier = Modifier
                    )
                    OutlinedTextField(
                        value = preparation,
                        label = {
                            Text(
                                text = "Description",
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                        },
                        onValueChange = { preparation = it },
                        placeholder = { Text(text = "Enter description") },
                        modifier = Modifier.height(with(LocalDensity.current) { 300.toDp() })
                    )

                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        value = prepTime?.toString() ?: "",
                        label = {
                            Text(
                                text = "Preparation time in minutes",
                                style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                        },
                        onValueChange = {
                            if (it == "") prepTime = null
                            val number = it.toIntOrNull()
                            if (number != null)
                                prepTime = number
                        },
                        placeholder = { Text(text = "Enter preparation time") },
                        modifier = Modifier
                    )

                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        value = price,
                        visualTransformation = PLNPriceVisualTransformation,
                        label = {
                            Text(
                                text = "Price", style = TextStyle(fontWeight = FontWeight.Bold)
                            )
                        },
                        onValueChange = {
                            var tmp = it
                            tmp = tmp.replace(',', '.')
                            if (EASY_PRICE_REGEX.matches(tmp)) {
                                price = tmp
                            }
                        },
                        placeholder = { Text(text = "Enter price") },
                        modifier = Modifier
                    )

                    Button(onClick = {
                        pickPictureLauncher.launch(arrayOf("image/*"))
                    }) {
                        Text("XD")
                    }

                    AsyncImage(
                        model = imageUri,
                        contentDescription = null
                    )


                    Button(onClick = onClick, enabled = isSendEnabled()) {
                        Text("Send")
                    }
                    CategoryDropdown(mealTimes) {
                        mealTime = it
                    }

                }
            })
        }

    }

}