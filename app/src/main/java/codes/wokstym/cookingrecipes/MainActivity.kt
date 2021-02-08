package codes.wokstym.cookingrecipes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import codes.wokstym.cookingrecipes.databinding.ActivityMainBinding
import dagger.hilt.android.HiltAndroidApp
import codes.wokstym.cookingrecipes.utils.startRecipeListActivity

@HiltAndroidApp
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initComponents()
    }

    private fun initComponents() {
        binding.recipeListButton.setOnClickListener {
            startRecipeListActivity()
        }
    }
}