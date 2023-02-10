package uz.rounded.baqlajon.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import uz.rounded.baqlajon.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}