package uz.rounded.baqlajon.presentation.ui.main.home.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.gone
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.core.extensions.visible
import uz.rounded.baqlajon.databinding.FragmentSearchBinding
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.home.adapter.SearchAllCourseAdapter

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): FragmentSearchBinding = FragmentSearchBinding.inflate(inflater)

    private val viewModel: SearchViewModel by viewModels()

    private val adapterSearchAllCourseAdapter by lazy {
        SearchAllCourseAdapter(requireContext()) {
            navigateWithArgs(
                R.id.action_searchFragment_to_courseDetailsFragment, bundleOf("ID" to it)
            )
        }
    }

    override fun created(view: View, savedInstanceState: Bundle?) {
        hideMainProgress()
        setAdapter()
        searchText()
        close()
        clickClose()
    }

    private fun searchText() = binding.search.addTextChangedListener {
        binding.close.visible()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                //search
                viewModel.searchAllCourse(it.toString().trim()).collectLatest {
                    //adapter for result data in search
                    adapterSearchAllCourseAdapter.addLoadStateListener { loadState ->
                        if (loadState.append.endOfPaginationReached) {
                            if (adapterSearchAllCourseAdapter.itemCount < 1) {
                                binding.animation.visible()
                                binding.searchRv.gone()
                            } else {
                                binding.animation.gone()
                                binding.searchRv.visible()
                            }
                        }
                    }
                    adapterSearchAllCourseAdapter.submitData(it)
                }

            }
        }
    }

    private fun close() = binding.apply {
        close.gone()
        requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private fun clickClose() = binding.close.setOnClickListener {
        binding.search.setText("")
        binding.close.gone()
    }


    private fun setAdapter() {
        binding.searchRv.adapter = adapterSearchAllCourseAdapter
    }

    override fun onResume() {
        super.onResume()
        close()
    }

    override fun onStop() {
        super.onStop()
        close()
    }

    override fun onStart() {
        super.onStart()
        close()
    }

}