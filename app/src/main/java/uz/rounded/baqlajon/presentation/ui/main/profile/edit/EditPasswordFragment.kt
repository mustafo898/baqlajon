package uz.rounded.baqlajon.presentation.ui.main.profile.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentEditPasswordBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class EditPasswordFragment : BaseFragment<FragmentEditPasswordBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentEditPasswordBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }
}