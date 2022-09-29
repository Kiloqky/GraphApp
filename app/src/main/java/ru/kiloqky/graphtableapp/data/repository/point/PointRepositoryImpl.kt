package ru.kiloqky.graphtableapp.data.repository.point

import kotlinx.coroutines.flow.flow
import ru.kiloqky.graphtableapp.data.api.apiworker.ApiWorker
import ru.kiloqky.graphtableapp.domain.repository.PointRepository
import javax.inject.Inject

class PointRepositoryImpl @Inject constructor(private val api: ApiWorker) : PointRepository {

    override suspend fun getPoint(count: Int) = flow { emit(api.getPoints(count)) }
}