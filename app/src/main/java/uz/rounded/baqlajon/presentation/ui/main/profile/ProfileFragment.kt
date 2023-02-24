package uz.rounded.baqlajon.presentation.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.AndroidEntryPoint
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.loadImage
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentProfileBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentProfileBinding = FragmentProfileBinding.inflate(inflater)

    @Inject
    lateinit var sharedPreference: SharedPreference
    private var nightMode = false

    override fun created(view: View, savedInstanceState: Bundle?) {

        binding.image.loadImage(requireContext(), sharedPreference.user.image)
        binding.name.text = buildString {
            append(sharedPreference.user.firstName)
            append(" ")
            append(sharedPreference.user.lastName)
        }

        binding.phone.text = sharedPreference.user.phoneNumber

        nightMode = sharedPreference.nightMode
        binding.moon.isChecked = nightMode

        nightMode()
        actions()

    }

    private fun nightMode() {
        binding.moon.setOnClickListener {
            if (!sharedPreference.nightMode) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPreference.nightMode = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPreference.nightMode = false
            }
        }
    }

    private fun actions() {
        binding.edit.setOnClickListener {
            navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        binding.payment.setOnClickListener {
            navigate(R.id.action_profileFragment_to_paymentHistoryFragment)
        }

        binding.about.setOnClickListener {
            navigate(R.id.action_profileFragment_to_aboutFragment)
        }

        binding.language.setOnClickListener {
            navigate(R.id.action_profileFragment_to_languageFragment)
        }
    }

}