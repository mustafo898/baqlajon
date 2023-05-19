package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.viewholder

import android.util.Log
import android.view.View
import com.downloader.*
import kotlinx.coroutines.*
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.blockClickable
import uz.rounded.baqlajon.core.extensions.getFileSize
import uz.rounded.baqlajon.core.utils.FileManager
import uz.rounded.baqlajon.core.utils.Prefs
import uz.rounded.baqlajon.databinding.ItemContentFileBinding
import uz.rounded.baqlajon.domain.model.main.course.ContentItemModel
import java.net.URL

class FileViewHolder(val binding: ItemContentFileBinding) : BaseViewHolder(binding.root),
    View.OnClickListener, OnDownloadListener, OnProgressListener {
    lateinit var url: String
    lateinit var prefs: Prefs
    lateinit var fileManager: FileManager
    private var downloadId = 0
    private lateinit var data: ContentItemModel


    override fun bindData(data: ContentItemModel) {
        Log.d("FKJDJFKS", "FileViewHolder: adapter")

        url = data.content
        this.data = data
        fileManager = FileManager(itemView.context)
        val filename = data.content.split("/").last()
        binding.setTitle(filename)
        prefs = Prefs(itemView.context)
        val fileSize = prefs.get("file${data.id}", 0)
        if (fileSize == 0) getFileSizeFromServer("file${data.id}")
        else {
            val width: Float = ((fileSize.toFloat() / 1024) / 1024)
            val text = "%.2f Mb".format(width)
//            binding.setDescription("$text FILE")
        }
        binding.status =
            if (fileManager.hasOfflineFile("${binding.root.context.getString(R.string.base_url)+"public/uploads"}$url", "file", true)) {
                binding.img.setImageResource(R.drawable.ic_file)
                2
            } else 0
        binding.onClick = this
        if (binding.status == 0 && prefs.get(prefs.autoDownload, false)) download()
    }

    private fun getFileSizeFromServer(key: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var fileSize: Int? = 0
            val job = async { fileSize = URL("${binding.root.context.getString(R.string.base_url)+"public/uploads"}$url").getFileSize() }
            job.await()
            withContext(Dispatchers.Main) {
                fileSize?.let {
                    prefs.save(key, it)
                    val width: Float = ((it.toFloat() / 1024) / 1024)
                    val text = "%.2f Mb".format(width)

                    binding.setDescription("$text FILE")
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        p0.blockClickable()
        when (binding.status) {
            0 -> download()
            1 -> cancelDownload()
            2 -> open()
        }
    }

    private fun download() {
        val downloadRequest = PRDownloader.download(
            "${binding.root.context.getString(R.string.base_url) + "public/uploads"}$url",
            "${fileManager.APP_FILE_DIRECTORY_PATH}/file/",
            fileManager.convertUrlToStoragePath(
                "${binding.root.context.getString(R.string.base_url) + "public/uploads"}$url",
                true
            )
        ).build()

        downloadId = downloadRequest.downloadId
        downloadRequest.onProgressListener = this
        binding.status = 1
        binding.img.setImageResource(R.drawable.ic_baseline_close_24)
        downloadRequest.start(this)

    }

    private fun cancelDownload() {
        PRDownloader.cancel(downloadId)
        binding.status = 0
        binding.img.setImageResource(R.drawable.ic_download)
    }

    private fun open() {
        val data = ContentItemModel(
            "", this.data.content, 5
        )
        data.content =
            fileManager.APP_FILE_DIRECTORY_PATH + "file/" + fileManager.convertUrlToStoragePath(
                "${binding.root.context.getString(R.string.base_url) + "public/uploads"}$url", true
            )[0]

        listener?.invoke(data)
    }

    override fun onDownloadComplete() {
        binding.status = 2
        binding.img.setImageResource(R.drawable.ic_file)
    }

    override fun onError(error: Error?) {
        binding.status = 0
        binding.img.setImageResource(R.drawable.ic_download)
    }

    override fun onProgress(progress: Progress?) {
        progress?.let {
            binding.pb.progress = (progress.currentBytes / progress.totalBytes).toInt()
            val current: Float = ((it.currentBytes / 1024) / 1024F)
            val total: Float = ((it.totalBytes / 1024) / 1024F)
            binding.setDescription("%.2f".format(current) + "/" + "%.2f".format(total) + " Mb")
        }
    }
}