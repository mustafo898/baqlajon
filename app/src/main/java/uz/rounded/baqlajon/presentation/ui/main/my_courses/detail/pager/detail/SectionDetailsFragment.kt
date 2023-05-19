package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.blockClickable
import uz.rounded.baqlajon.core.extensions.navigateWithArgs
import uz.rounded.baqlajon.core.extensions.popBackStack
import uz.rounded.baqlajon.core.utils.Constants
import uz.rounded.baqlajon.core.utils.SharedPreference
import uz.rounded.baqlajon.databinding.FragmentSectionDetailsBinding
import uz.rounded.baqlajon.domain.model.main.course.ContentItemModel
import uz.rounded.baqlajon.presentation.ui.BaseFragment
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.adapter.ContentAdapter
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.callback.ContentAdapterCallBack
import java.io.File
import javax.inject.Inject

@AndroidEntryPoint
class SectionDetailsFragment : BaseFragment<FragmentSectionDetailsBinding>(),
    ContentAdapterCallBack,
    View.OnClickListener {

    private val viewModel: DetailsViewModel by viewModels()
    private var lessonUrl = ""

    @Inject
    lateinit var prefs: SharedPreference

    private val adapter by lazy {
        ContentAdapter(requireContext())
    }
    var download = false

    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ) = FragmentSectionDetailsBinding.inflate(inflater)

    private var id = ""
    private var isOpen = false
    var state = 0

    override fun created(view: View, savedInstanceState: Bundle?) {

        if (arguments != null) {
            id = requireArguments().getString("ID") as String
        }

        binding.adapter = adapter
        binding.onClick = this@SectionDetailsFragment
        adapter.callBack = this@SectionDetailsFragment

        observe()
//        playVideo()
        binding.btn.cardView.setOnClickListener {
            viewModel.finishVideo(id)
        }
        binding.multiDataRv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                state = newState
            }
        })
        lifecycleScope.launchWhenStarted {
            viewModel.finish.collectLatest {
                it.data?.let {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
                }
            }
        }
        download = prefs.get(prefs.autoDownload, false)
    }

//    private fun playVideo() = binding.play.setOnClickListener {
//        navigateWithArgs(
//            R.id.action_sectionDetailsFragment_to_playerFragment, bundleOf("ID" to lessonUrl)
//        )
//    }

    override fun onItemClick(data: ContentItemModel) {
        Log.d("LFDSNLF", data.content)
        when (data.type) {
            Constants.Type.VIDEO -> {
                navigateWithArgs(
                    R.id.action_sectionDetailsFragment_to_playerFragment,
                    bundleOf("ID" to data.content)
                )
            }
//            Constants.Type.AUDIO -> {
//                playAudio(data.content)
//            }
            else -> {
                openFile(data)
            }
        }
    }

    var recyclerViewState: Parcelable? = null

    override fun onPause() {
        hideKeyBoard()
        recyclerViewState = binding.multiDataRv.layoutManager?.onSaveInstanceState()
        prefs.save(prefs.autoDownload, download)
        super.onPause()
    }

    private fun openFile(data: ContentItemModel) {
        context?.let {
            val intent = Intent(Intent.ACTION_VIEW)
            val targetUri = Uri.fromFile(File(data.content))
            if (targetUri.toString().contains(".doc") || targetUri.toString().contains(".docx")) {
                // Word document
                intent.setDataAndType(targetUri, "application/msword");
            } else if (targetUri.toString().contains(".pdf")) {
                // PDF file
                intent.setDataAndType(targetUri, "application/pdf")
            } else if (targetUri.toString().contains(".ppt") || targetUri.toString()
                    .contains(".pptx")
            ) {
                // Powerpoint file
                intent.setDataAndType(targetUri, "application/vnd.ms-powerpoint");
            } else if (targetUri.toString().contains(".xls") || targetUri.toString()
                    .contains(".xlsx")
            ) {
                // Excel file
                intent.setDataAndType(targetUri, "application/vnd.ms-excel")
            } else
                intent.setDataAndType(targetUri, "application/*")
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(intent)

        }
    }

    var data: ArrayList<ContentItemModel> = ArrayList()

    private fun setData(data: List<ContentItemModel>) {
        Log.d("TTT", "setData: $data")
//        val newdata = ArrayList<ContentOfTopic>()
//        newdata.addAll(data)
//        newdata.add(ContentOfTopic(1500, data[0].langId, data[0].topicId, "/contents\\/BC\\/122\\/uz\\/1221_uz.mp4",
//            data.size, ContentConstants.Type.AUDIO))
        adapter.swapData(data)
        Log.d("FKJDJFKS", "setData: data set")
        this.data.clear()
        this.data.addAll(data)
    }

    override fun onClick(p0: View?) {
        p0?.blockClickable()
        when (p0?.id) {
            R.id.back -> {
                popBackStack()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        recyclerViewState?.let {
            binding.multiDataRv.layoutManager?.onRestoreInstanceState(it)
        }
    }

    private fun observe() {
        viewModel.getDetailVideo(id)

        lifecycleScope.launchWhenStarted {
            viewModel.detail.collectLatest {
                it.data?.let { p ->
//                    lessonUrl = buildString {
//                        append(p.videoUrl)
//                    }
                    setData(p.content)
                    Log.d("FKJDJFKS", "observe: ${p.courseId}")

//                    binding.apply {
//                        title.text = p.title
//                        desc.text = p.description
//                        if (p.isFree) {
//                            isOpen = true
//                            notification.gone()
//                        } else {
//                            isOpen = false
//                            notification.visible()
//                        }
//                        image2.loadImage(requireContext(), p.imageUrl)
//                    }
                    hideMainProgress()
                }
                if (it.isLoading) {
                    showMainProgress()
                }
                if (it.error.isNotBlank()) {
                    hideMainProgress()
                }
            }
        }
    }
//    private lateinit var player: SimpleExoPlayer
//
//    private fun playAudio(url: String) {
//        context?.let {
//            player = ExoPlayerFactory.newSimpleInstance(it)
//            val dataSource = DefaultDataSourceFactory(it, Util.getUserAgent(it, "Avtostart"))
//            val mediaSource = ExtractorMediaSource.Factory(dataSource).setExtractorsFactory(
//                DefaultExtractorsFactory()
//            ).createMediaSource(Uri.parse(url))
//            player.prepare(mediaSource, true, false)
//            player.playWhenReady = true
//            val view: DialogAudioPlayerBinding =
//                DataBindingUtil.inflate(layoutInflater, R.layout.dialog_audio_player, null, false)
//            view.theme = themeManager.currentTheme
//            view.playerView.player = player
//            bottomSheetDialog = BottomSheetDialog(it)
//            bottomSheetDialog.setContentView(view.root)
//            bottomSheetDialog.show()
//        }
//    }

}