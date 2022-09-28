package ru.kiloqky.graphtableapp.api.apiworker

import ru.kiloqky.graphtableapp.pojo.response.PointsResponse

interface ApiWorker {

    suspend fun getPoints(count: Int): PointsResponse
}