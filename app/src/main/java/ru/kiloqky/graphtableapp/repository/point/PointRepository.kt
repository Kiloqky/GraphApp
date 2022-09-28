package ru.kiloqky.graphtableapp.repository.point

import kotlinx.coroutines.flow.Flow
import ru.kiloqky.graphtableapp.pojo.response.PointsResponse

interface PointRepository {

    suspend fun getPoint(count: Int): Flow<PointsResponse>
}