package uz.rounded.baqlajon.presentation

import android.Manifest
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.animateToolBarTittle
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.showToast
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.core.utils.ControlLanguage
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.ActivityMainBinding
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    @Inject
    lateinit var sharedPreference: SharedPreference

    private val isToolBarGone = mutableListOf(
        R.id.homeFragment, R.id.myCoursesFragment, R.id.balanceFragment, R.id.profileFragment
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentMain) as NavHostFragment
        navController = navHostFragment.findNavController()

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.toolbar.title.text = navController.currentDestination?.label.toString()
            animateToolBarTittle(binding.toolbar.title)
            if (isToolBarGone.contains(destination.id)) {
                hideProgress()
                binding.bottomNav.visible()
                binding.toolbar.toolbar.gone()
            } else {
                showProgress()
                binding.bottomNav.gone()
                binding.toolbar.toolbar.visible()
            }

            if (destination.id == R.id.searchFragment) binding.toolbar.search.visible()
            else binding.toolbar.search.gone()
        }

        binding.toolbar.back.setOnClickListener {
            navController.popBackStack()
        }
        setupWithNavController(binding.bottomNav, navController)
        requestPermission()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase?.let { ControlLanguage.setLocale(it) })
    }

    fun hideProgress() {
        binding.progress.gone()
    }

    fun showProgress() {
        binding.progress.visible()
    }

    fun hideProgress1() {
        binding.progressBar1.gone()
    }

    fun showProgress1() {
        binding.progressBar1.visible()
    }

    fun setMainToolbarText(text: String) {
        binding.toolbar.title.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        sharedPreference.type = 0
    }

    override fun onPause() {
        super.onPause()
        Log.d("DJFKNSF", "onPause: activity")
    }

    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncherResult.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private val requestPermissionLauncherResult = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        showToast(isGranted.toString())
    }
}