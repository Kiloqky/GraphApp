package ru.kiloqky.graphtableapp.domain.interactors

import android.graphics.Bitmap
import kotlinx.coroutines.flow.map
import ru.kiloqky.graphtableapp.data.api.apiworker.ApiErrorResponse
import ru.kiloqky.graphtableapp.data.api.apiworker.ApiSuccessResponse
import ru.kiloqky.graphtableapp.domain.repository.GalleryRepository
import ru.kiloqky.graphtableapp.domain.repository.PointRepository
import ru.kiloqky.graphtableapp.pojo.model.toModel
import ru.kiloqky.graphtableapp.utils.exceptions.NetworkException
import javax.inject.Inject

class PointInteractor @Inject constructor(
    private val pointRepository: PointRepository,
    private val galleryRepository: GalleryRepository
) {

    suspend fun getPoints(count: Int) =
        pointRepository.getPoint(count).map { response ->
            when (response) {
                is ApiSuccessResponse -> response.body.toModel().sortedBy { it.x }
                is ApiErrorResponse -> throw NetworkException(response.message)
            }
        }

    suspend fun saveToGallery(bitmap: Bitmap) = galleryRepository.saveImage(bitmap)
}