package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.sections

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.databinding.FragmentCourseSectionsBinding
import uz.rounded.baqlajon.domain.model.main.course.VideoModel
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.adapter.CourseSectionsAdapter

class CourseSectionsFragment(private val list: List<VideoModel>) :
    BaseFragment<FragmentCourseSectionsBinding>() {

    private var id: String = ""

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentCourseSectionsBinding.inflate(inflater)

    private val adapter by lazy {
        CourseSectionsAdapter {
            showToast(it)
//            val intent = Intent(activity, VideoPlayActivity::class.java)
//            intent.putExtra("ID", it)
//            startActivity(intent)
            navigateWithArgs(
                R.id.sectionDetailsFragment, bundleOf("ID" to it)
            )
        }
    }

    override fun created(view: View, savedInstanceState: Bundle?) {
        binding.list.adapter = adapter

        adapter.setList(list)
        //click()
    }

    private fun click() {
//            findNavController().navigate(
//                R.id.action_courseDetailsFragment_to_sectionDetailsFragment,
//                bundleOf("ID" to id)
//            )
        //Log.d("jkdnf", "created: $it")

    }

}