package ru.kiloqky.graphtableapp.data.api.apiworker

import retrofit2.Response
import ru.kiloqky.graphtableapp.data.api.Api
import ru.kiloqky.graphtableapp.pojo.response.PointsResponse
import javax.inject.Inject

class ApiWorkerImpl @Inject constructor(private val api: Api) : ApiWorker {

    override suspend fun getPoints(count: Int): ApiResponse<PointsResponse> =
        handleResponse { api.getPoints(count) }

    private suspend inline fun <T> handleResponse(crossinline action: suspend () -> Response<T>): ApiResponse<T> {
        return ApiResponse.create(action.invoke())
    }
}