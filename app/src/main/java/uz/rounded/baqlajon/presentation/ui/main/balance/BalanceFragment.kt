package uz.rounded.baqlajon.presentation.ui.main.balance

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.databinding.FragmentBalanceBinding
import uz.roundedllc.tmkeld.presentation.BaseFragment


class BalanceFragment : BaseFragment<FragmentBalanceBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBalanceBinding = FragmentBalanceBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }


}