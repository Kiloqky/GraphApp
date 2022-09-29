package ru.kiloqky.graphtableapp.data.api.apiworker

import ru.kiloqky.graphtableapp.pojo.response.PointsResponse

interface ApiWorker {

    suspend fun getPoints(count: Int): ApiResponse<PointsResponse>
}