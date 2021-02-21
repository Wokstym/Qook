package codes.wokstym.cookingrecipes.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import codes.wokstym.cookingrecipes.databinding.ActivityAddRecipeBinding
import codes.wokstym.cookingrecipes.utils.showProgressBar

class AddRecipeActivity : AppCompatActivity() {

    lateinit var binding: ActivityAddRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showProgressBar()
    }
}