package uz.rounded.baqlajon.presentation.ui.main.profile.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.rounded.baqlajon.databinding.FragmentPaymentHistoryBinding
import uz.rounded.baqlajon.presentation.ui.main.profile.history.adapter.PaymentHistoryAdapter
import uz.rounded.baqlajon.presentation.ui.BaseFragment

class PaymentHistoryFragment : BaseFragment<FragmentPaymentHistoryBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentPaymentHistoryBinding.inflate(inflater)


    private val adapter by lazy {
        PaymentHistoryAdapter {

        }
    }

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter

        val list = mutableListOf<String>()
        for (i in 0 until 10) {
            list.add("")
        }

        adapter.submitList(list)
    }
}