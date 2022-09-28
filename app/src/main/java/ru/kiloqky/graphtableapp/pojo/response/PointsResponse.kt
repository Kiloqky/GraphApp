package ru.kiloqky.graphtableapp.pojo.response

import com.google.gson.annotations.SerializedName

data class PointsResponse(
    @SerializedName("points")
    val list: List<PointResponse>
)

data class PointResponse(
    @SerializedName("x")
    val x: Double,
    @SerializedName("y")
    val y: Double
)

