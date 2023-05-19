package uz.rounded.baqlajon.core.utils

import android.content.Context
import android.util.Base64
import uz.rounded.baqlajon.core.extensions.getFileSize
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.crypto.Cipher
import javax.crypto.CipherOutputStream
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class FileManager @Inject constructor(val context: Context) {

    var processUpdate: OnProcessUpdate? = null

    private val myKey =
        "1234567890123456".toByteArray()

    var cancel: Boolean = false

    val APP_FILE_DIRECTORY_PATH: String =
        "${context.filesDir}/Baqlajon/"

    fun hasOfflineFile(url: String, dir: String, isEncrypt: Boolean): Boolean {
        return try {
            File("$APP_FILE_DIRECTORY_PATH$dir/", convertUrlToStoragePath(url, isEncrypt)).exists()
        } catch (ex: Exception) {
            false
        }
    }

    suspend fun downloadFile(httpUrl: String, isEncrypt: Boolean): MyResponse<String> {
        try {
            val url = URL(httpUrl)
            val fileSize = url.getFileSize()
            val connection = url.openConnection() as HttpURLConnection?
            val dir = File(APP_FILE_DIRECTORY_PATH)
            if (!dir.exists())
                dir.mkdirs()
            return if (fileSize != null && connection != null) {
                val filename = convertUrlToStoragePath(httpUrl, isEncrypt)
                connection.connect()
                val file = File(APP_FILE_DIRECTORY_PATH, filename)
                val inputStream = BufferedInputStream(url.openStream())
                val skeySpec: SecretKeySpec = SecretKeySpec(myKey, "AES")
                val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
                val outputStream = if (isEncrypt) CipherOutputStream(
                    FileOutputStream(file, false),
                    cipher
                ) else BufferedOutputStream(FileOutputStream(file, false))
                val buf = ByteArray(1024)
                var total = 0
                while (true) {
                    val n = inputStream.read(buf)
                    if (n <= 0) break
                    if (cancel) {
                        cancel = false
                        inputStream.close()
                        outputStream.close()
                        file.delete()
                        return MyResponse.Error(NullPointerException())
                    }
                    outputStream.write(buf)
                    total += n
                    processUpdate?.onUpdate(total * 100 / fileSize)
                }
                inputStream.close()
                outputStream.close()
                MyResponse.Success(file.absolutePath)
            } else MyResponse.Error(NullPointerException())
        } catch (e: Exception) {
            return MyResponse.Error(e)
        }
    }

    fun convertUrlToStoragePath(url: String, isEncrypt: Boolean): String {
        var filename: String = url.split("/").last()
        if (isEncrypt) {
            filename = Base64.encodeToString(filename.toByteArray(), Base64.NO_WRAP)
        }
        return filename
    }

    interface OnProcessUpdate {
        fun onUpdate(process: Int)
    }

}