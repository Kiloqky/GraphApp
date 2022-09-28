package ru.kiloqky.graphtableapp.repository.gallery

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

class GalleryRepositoryImpl @Inject constructor(private val applicationContext: Context) :
    GalleryRepository {

    override suspend fun saveImage(bitmap: Bitmap) = flow {
        val fileName = "Graph ${SimpleDateFormat.getDateTimeInstance().format(Date())}"
        var stream: OutputStream?

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            applicationContext.contentResolver.also { contentResolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imageUri = contentResolver.insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    contentValues
                )

                stream = imageUri?.let { contentResolver.openOutputStream(it) }
            }
        } else {
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, fileName)
            stream = withContext(Dispatchers.IO) {
                FileOutputStream(image)
            }
        }

        stream?.use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }

        emit(Unit)
    }
}