package uz.rounded.baqlajon.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.animateToolBarTittle
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.invisible
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.ActivityStartBinding
import javax.inject.Inject

@AndroidEntryPoint
class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding
    private lateinit var navController: NavController

    @Inject
    lateinit var sharedPreference: SharedPreference

    private val isToolBarGone = mutableListOf(
        R.id.welcomeFragment,
        R.id.authFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentStart) as NavHostFragment
        navController = navHostFragment.findNavController()
        lifecycleScope.launch {
            delay(1000)
            if (sharedPreference.hasToken) {
                startActivity(Intent(this@StartActivity, MainActivity::class.java))
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title.text = navController.currentDestination?.label.toString()
            animateToolBarTittle(binding.toolbar.title)
            if (isToolBarGone.contains(destination.id)
            ) {
                binding.toolbar.toolbar.invisible()
            } else {
                binding.toolbar.toolbar.visible()
            }
        }
        binding.toolbar.back.setOnClickListener {
            navController.popBackStack()
        }

    }
}