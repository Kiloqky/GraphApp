package ru.kiloqky.graphtableapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.kiloqky.graphtableapp.data.api.apiworker.ApiResponse
import ru.kiloqky.graphtableapp.data.pojo.response.PointsResponse

interface PointRepository {

    suspend fun getPoint(count: Int): Flow<ApiResponse<PointsResponse>>
}