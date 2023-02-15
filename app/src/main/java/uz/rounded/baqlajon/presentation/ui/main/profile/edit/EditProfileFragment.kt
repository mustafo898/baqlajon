package uz.rounded.baqlajon.presentation.ui.main.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigate
import uz.rounded.baqlajon.databinding.FragmentEditProfileBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEditProfileBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {
        val regions = resources.getStringArray(R.array.gender)
        val adapter = ArrayAdapter(
            requireContext(), R.layout.item_gender, regions
        )

        binding.gender.adapter = adapter
        binding.date.adapter = adapter

        binding.changePassword.setOnClickListener {
            navigate(R.id.action_editProfileFragment_to_editPasswordFragment)
        }
        binding.changePhone.setOnClickListener {
            navigate(R.id.action_editProfileFragment_to_editPhoneFragment)
        }
    }
}