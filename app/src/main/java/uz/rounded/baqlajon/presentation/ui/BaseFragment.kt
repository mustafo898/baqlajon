package uz.rounded.baqlajon.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.core.extensions.showToast
import uz.rounded.baqlajon.presentation.MainActivity
import uz.rounded.baqlajon.presentation.StartActivity
import javax.inject.Inject

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    protected lateinit var binding: Binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return if (view != null) view
        else {
            binding = createBinding(inflater, container)
            binding.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        created(view, savedInstanceState)
//        loadObserver()
    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    abstract fun created(view: View, savedInstanceState: Bundle?)

    fun showToast(str: String) {
        requireContext().showToast(str)
    }

    fun showMainProgress() {
        (activity as MainActivity).showProgress()
    }

    fun hideMainProgress() {
        (activity as MainActivity).hideProgress()
    }

    fun showMainProgress1() {
        (activity as MainActivity).showProgress1()
    }

    fun hideMainProgress1() {
        (activity as MainActivity).hideProgress1()
    }

    fun showStartProgress() {
        (activity as StartActivity).showProgress()
    }

    fun hideStartProgress() {
        (activity as StartActivity).hideProgress()
    }

}