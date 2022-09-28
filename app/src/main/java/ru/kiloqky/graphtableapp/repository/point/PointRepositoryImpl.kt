package ru.kiloqky.graphtableapp.repository.point

import kotlinx.coroutines.flow.flow
import ru.kiloqky.graphtableapp.api.apiworker.ApiWorker
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(private val api: ApiWorker) : PointRepository {

    override suspend fun getPoint(count: Int) = flow { emit(api.getPoints(count)) }
}