package ru.kiloqky.graphtableapp.ui.points.viewModels

import android.content.res.Resources
import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.kiloqky.graphtableapp.R
import ru.kiloqky.graphtableapp.interactors.PointInteractor
import ru.kiloqky.graphtableapp.pojo.model.toListEntry
import javax.inject.Inject

@HiltViewModel
class PointViewModel @Inject constructor(
    private val interactor: PointInteractor,
    private val resources: Resources
) : ViewModel() {

    private val _pointsSharedFlow = MutableSharedFlow<List<Entry>>(
        1,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val pointsSharedFlow = _pointsSharedFlow.asSharedFlow()

    private val _gallerySavedSharedFlow = MutableSharedFlow<Unit>()
    val gallerySavedSharedFlow = _gallerySavedSharedFlow.asSharedFlow()

    private val _errorSharedFlow = MutableSharedFlow<String>()
    val errorSharedFlow = _errorSharedFlow.asSharedFlow()

    fun getPoints(count: String) {
        viewModelScope.launch {
            kotlin.runCatching { count.toInt() }.onSuccess { count ->
                interactor.getPoints(count)
                    .map { it.toListEntry() }
                    .onEach { _pointsSharedFlow.emit(it) }
                    .catch {
                        _errorSharedFlow.emit(
                            it.message ?: resources.getString(R.string.text_unknown_error)
                        )
                    }.launchIn(this)
            }.onFailure {
                _errorSharedFlow.emit(
                    it.message ?: resources.getString(R.string.text_unknown_error)
                )
            }
        }
    }

    fun saveToGallery(bitmap: Bitmap) {
        viewModelScope.launch {
            interactor.saveToGallery(bitmap)
                .onEach { _gallerySavedSharedFlow.emit(it) }
                .catch {
                    _errorSharedFlow.emit(
                        it.message ?: resources.getString(R.string.text_unknown_error)
                    )
                }.launchIn(this)
        }
    }

}