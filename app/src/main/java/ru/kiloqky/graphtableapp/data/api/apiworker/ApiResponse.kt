package ru.kiloqky.graphtableapp.data.api.apiworker

import retrofit2.Response

sealed class ApiResponse<T> {

    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            val body = response.body()
            return if (response.isSuccessful && body != null) {
                ApiSuccessResponse(body)
            } else {
                ApiErrorResponse(response.errorBody()?.string() ?: response.message())
            }
        }
    }
}

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

data class ApiErrorResponse<T>(val message: String?) : ApiResponse<T>()
