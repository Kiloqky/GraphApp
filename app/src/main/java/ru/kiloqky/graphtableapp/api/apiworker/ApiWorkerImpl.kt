package ru.kiloqky.graphtableapp.api.apiworker

import retrofit2.Response
import ru.kiloqky.graphtableapp.api.Api
import ru.kiloqky.graphtableapp.exceptions.NetworkException
import ru.kiloqky.graphtableapp.pojo.response.PointsResponse
import javax.inject.Inject

class ApiWorkerImpl @Inject constructor(private val api: Api) : ApiWorker {

    private suspend inline fun <T> handleResponse(crossinline action: suspend () -> Response<T>): T {
        return try {
            when (val result = ApiResponse.create(action.invoke())) {
                is ApiSuccessResponse -> {
                    result.body ?: throw NetworkException()
                }
                is ApiErrorResponse -> {
                    throw NetworkException(result.message)
                }
            }
        } catch (e: Throwable) {
            throw e
        }
    }

    private suspend inline fun handleResponseWithoutAnswer(crossinline action: suspend () -> Response<Unit>) {
        return try {
            when (val result = ApiResponse.create(action.invoke())) {
                is ApiSuccessResponse -> {
                    /* no-op */
                }
                is ApiErrorResponse -> {
                    throw NetworkException(result.message)
                }
            }
        } catch (e: Throwable) {
            throw e
        }
    }

    override suspend fun getPoints(count: Int): PointsResponse =
        handleResponse { api.getPoints(count) }

}