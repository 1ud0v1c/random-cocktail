package com.ludovic.vimont.randomcocktail.helper

import android.content.Context
import android.os.Environment
import java.io.*

object FileHelper {
    fun readFileFromAssets(context: Context, filename: String): String {
        try {
            val stream: InputStream = context.assets.open(filename)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            return String(buffer)
        } catch (e: IOException) {
            throw IOException(e.localizedMessage)
        }
    }

    fun writeToFile(context: Context, filename: String, content: String) {
        if (content.isEmpty()) {
            return
        }
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
            filename
        )
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileOutputStream = FileOutputStream(file, true)
        val bufferedWriter = BufferedWriter(OutputStreamWriter(fileOutputStream))
        bufferedWriter.write(content)
    }
}