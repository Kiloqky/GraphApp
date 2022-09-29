package ru.kiloqky.graphtableapp.domain

import ru.kiloqky.graphtableapp.data.pojo.response.PointsResponse
import ru.kiloqky.graphtableapp.presentation.models.Point


fun PointsResponse.toModel() = list.map { Point(it.x, it.y) }