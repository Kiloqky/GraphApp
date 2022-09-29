package ru.kiloqky.graphtableapp.data.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.kiloqky.graphtableapp.data.pojo.response.PointsResponse

interface Api {

    @GET("/api/test/points")
    suspend fun getPoints(@Query("count") count: Int): Response<PointsResponse>
}