package ru.kiloqky.graphtableapp.repository.gallery

import android.graphics.Bitmap
import kotlinx.coroutines.flow.Flow


interface GalleryRepository {

    suspend fun saveImage(bitmap: Bitmap): Flow<Unit>
}