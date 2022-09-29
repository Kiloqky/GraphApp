package ru.kiloqky.graphtableapp.presentation.models

import android.os.Parcelable
import com.github.mikephil.charting.data.Entry
import kotlinx.parcelize.Parcelize

@Parcelize
data class Point(
    val x: Double,
    val y: Double
) : Parcelable

fun List<Point>.toListEntry() = map { Entry(it.x.toFloat(), it.y.toFloat()) }
