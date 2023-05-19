package uz.rounded.baqlajon.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import uz.rounded.baqlajon.core.extensions.showToast
import uz.rounded.baqlajon.presentation.MainActivity
import uz.rounded.baqlajon.presentation.StartActivity

abstract class BaseFragment<Binding : ViewBinding> : Fragment() {

    protected lateinit var binding: Binding
    protected lateinit var dataBinding: ViewDataBinding

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

    fun hideKeyBoard() {
        val view = activity?.currentFocus ?: View(activity)
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}