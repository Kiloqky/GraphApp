package ru.kiloqky.graphtableapp.interactors

import android.graphics.Bitmap
import kotlinx.coroutines.flow.map
import ru.kiloqky.graphtableapp.pojo.model.toModel
import ru.kiloqky.graphtableapp.repository.gallery.GalleryRepository
import ru.kiloqky.graphtableapp.repository.point.PointRepository
import javax.inject.Inject

class PointInteractor @Inject constructor(
    private val pointRepository: PointRepository,
    private val galleryRepository: GalleryRepository
) {

    suspend fun getPoints(count: Int) =
        pointRepository.getPoint(count).map { response -> response.toModel().sortedBy { it.x } }

    suspend fun saveToGallery(bitmap: Bitmap) = galleryRepository.saveImage(bitmap)
}