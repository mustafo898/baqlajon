package uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.viewholder

import android.media.MediaPlayer
import android.net.Uri
import android.view.View
import com.downloader.*
import kotlinx.coroutines.*
import org.ost.avtostart.util.Prefs
import uz.rounded.baqlajon.R
import uz.rounded.baqlajon.core.extensions.blockClickable
import uz.rounded.baqlajon.core.extensions.getFileSize
import uz.rounded.baqlajon.core.utils.FileManager
import uz.rounded.baqlajon.databinding.ItemContentVideoBinding
import uz.rounded.baqlajon.presentation.ui.main.my_courses.detail.pager.detail.video.ContentOfTopic
import java.net.URL
import java.util.concurrent.TimeUnit

class VideoViewHolder(val binding: ItemContentVideoBinding) : BaseViewHolder(binding.root),
    View.OnClickListener, OnDownloadListener, OnProgressListener {
    lateinit var url: String
    lateinit var prefs: Prefs
    lateinit var fileManager: FileManager
    private var downloadId = 0
    private lateinit var data: ContentOfTopic

    override fun bindData(data: ContentOfTopic) {
        this.data = data
        binding.videoUrl = data.content
        prefs = Prefs(itemView.context)
        fileManager = FileManager(itemView.context)
        url = data.content
        binding.status = if (fileManager.hasOfflineFile(url, "video", true)) {
            binding.download.visibility = View.GONE
            videoLenth()
            2
        } else {
            val fileSize = prefs.get("file${data.id}", 0)
            if (fileSize == 0) getFileSizeFromServer("file${data.id}")
            else {
                val width: Float = ((fileSize.toFloat() / 1024) / 1024)
                val text = "%.2f Mb".format(width)
                binding.description = text
            }
            binding.download.visibility = View.VISIBLE
            0
        }
        binding.onClick = this
        if (binding.status == 0 && prefs.get(prefs.autoDownload, false)) download()
    }

    private fun getFileSizeFromServer(key: String) {
        CoroutineScope(Dispatchers.IO).launch {
            var fileSize: Int? = 0
            val job = async { fileSize = URL(url).getFileSize() }
            job.await()
            withContext(Dispatchers.Main) {
                fileSize?.let {
                    prefs.save(key, it)
                    val width: Float = ((it.toFloat() / 1024) / 1024)
                    val text = "%.2f Mb".format(width)
                    binding.description = "$text"
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        p0.blockClickable()
        when (binding.status) {
            0 -> download()
            1 -> {
                if (p0?.id == R.id.play) return
                cancelDownload()
            }
            2 -> open()
        }
    }

    private fun download() {
        val downloadRequest = PRDownloader.download(
            url,
            "${fileManager.APP_FILE_DIRECTORY_PATH}video/",
            fileManager.convertUrlToStoragePath(url, true)
        ).build()

        downloadId = downloadRequest.downloadId
        downloadRequest.onProgressListener = this
        binding.status = 1
        binding.img.setImageResource(R.drawable.ic_baseline_close_24)
        downloadRequest.start(this)

    }

    private fun videoLenth() {
        val mp = MediaPlayer.create(
            itemView.context, Uri.parse(
                fileManager.APP_FILE_DIRECTORY_PATH + "video/" + fileManager.convertUrlToStoragePath(
                    url, true
                )
            )
        )
        try {
            val duration = mp.duration.toLong()
            mp.release()
            binding.description = "%d:%02d".format(
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) % 60
            )
        } catch (x: Exception) {

        }
    }

    private fun cancelDownload() {
        PRDownloader.cancel(downloadId)
        binding.status = 0
        binding.img.setImageResource(R.drawable.ic_download)
    }

    private fun open() {
        val data = this.data.copy()
        data.content =
            fileManager.APP_FILE_DIRECTORY_PATH + "video/" + fileManager.convertUrlToStoragePath(
                url, true
            )
        listener?.invoke(data)
    }

    override fun onDownloadComplete() {
        binding.status = 2
        binding.download.visibility = View.GONE
        videoLenth()
    }

    override fun onError(error: Error?) {
        binding.status = 0
        binding.img.setImageResource(R.drawable.ic_music)
    }

    override fun onProgress(progress: Progress?) {
        progress?.let {
            binding.pb.progress = (progress.currentBytes / progress.totalBytes).toInt()
            val current: Float = ((it.currentBytes / 1024) / 1024F)
            val total: Float = ((it.totalBytes / 1024) / 1024F)
            binding.description = "%.2f".format(current) + "/" + "%.2f".format(total) + " Mb"
        }
    }
}