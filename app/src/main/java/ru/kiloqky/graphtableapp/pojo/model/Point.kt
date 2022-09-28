package ru.kiloqky.graphtableapp.pojo.model

import android.os.Parcelable
import com.github.mikephil.charting.data.Entry
import kotlinx.parcelize.Parcelize
import ru.kiloqky.graphtableapp.pojo.response.PointsResponse

@Parcelize
data class Point(
    val x: Double,
    val y: Double
) : Parcelable

fun PointsResponse.toModel() = list.map { Point(it.x, it.y) }

fun List<Point>.toListEntry() = map { Entry(it.x.toFloat(), it.y.toFloat()) }
