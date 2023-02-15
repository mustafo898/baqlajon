package uz.rounded.baqlajon.presentation.ui.main.balance.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentShopBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class ShopFragment : BaseFragment<FragmentShopBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentShopBinding.inflate(inflater)

    override fun created(view: View, savedInstanceState: Bundle?) {

    }
}