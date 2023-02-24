package uz.rounded.baqlajon.presentation.ui.main.balance.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.databinding.FragmentShopBinding
import uz.rounded.baqlajon.presentation.dialog.BuyDialog
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.balance.shop.adapter.ShopAdapter

@AndroidEntryPoint
class ShopFragment : BaseFragment<FragmentShopBinding>() {
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentShopBinding.inflate(inflater)

    private val viewModel: ShopViewModel by viewModels()

    private val adapter by lazy {
        ShopAdapter(requireContext()) {
            viewModel.buyGift(it)
        }
    }

    private val dialog by lazy {
        BuyDialog(requireContext())
    }

    override fun created(view: View, savedInstanceState: Bundle?) {

        binding.list.adapter = adapter

        viewModel.getGiftList()

        lifecycleScope.launchWhenStarted {
            viewModel.shopList.collectLatest {
                it.data?.let { p ->
                    adapter.submitList(p)
                    hideMainProgress()
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.buy.collectLatest {
                it.data?.let { p ->
                    dialog.setCongratulations()
                    dialog.show()
                }
                if (it.error.isNotBlank()) {
                    dialog.setError(it.error)
                    dialog.show()
                }
            }
        }
    }
}