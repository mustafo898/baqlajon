package uz.roundedllc.tmkeld.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

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
    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): Binding

    abstract fun created(view: View, savedInstanceState: Bundle?)

    fun showToast(str: String) {
        Toast.makeText(requireContext(), str, Toast.LENGTH_SHORT).show()
    }

}