package ru.kiloqky.graphtableapp.presentation.points

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import ru.kiloqky.graphtableapp.R
import ru.kiloqky.graphtableapp.databinding.ActivityPointBinding
import ru.kiloqky.graphtableapp.presentation.points.adapter.PointTableAdapter
import ru.kiloqky.graphtableapp.presentation.points.viewModels.PointViewModel
import ru.kiloqky.graphtableapp.utils.extensions.launchWhenStarted
import ru.kiloqky.graphtableapp.utils.loadBitmapFromView

@AndroidEntryPoint
class PointActivity : FragmentActivity(R.layout.activity_point) {

    private var _binding: ActivityPointBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PointViewModel>()

    private val adapter by lazy(::PointTableAdapter)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPointBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initTable()
        initSubscribe()
        initBtns()
    }

    private fun initBtns() {
        binding.btnGo.setOnClickListener {
            viewModel.getPoints(binding.countField.text.toString())
        }

        binding.btnSaveToGallery.setOnClickListener {
            loadBitmapFromView(binding.graph)?.let { bitmap -> viewModel.saveToGallery(bitmap) }
        }
    }

    private fun initSubscribe() {
        viewModel.gallerySavedSharedFlow.onEach {
            Snackbar.make(
                binding.root,
                getString(R.string.text_saved_in_gallery),
                Snackbar.LENGTH_LONG
            ).show()
        }.launchWhenStarted { lifecycle }

        viewModel.pointsSharedFlow.onEach {
            binding.graph.changePlaceMark(it)
            adapter.submitList(it)
        }.launchWhenStarted { lifecycle }

        viewModel.errorSharedFlow.onEach {
            Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG).show()
        }.launchWhenStarted { lifecycle }
    }

    private fun initTable() {
        binding.table.addItemDecoration(
            DividerItemDecoration(applicationContext, LinearLayoutManager.HORIZONTAL)
                .apply {
                    ContextCompat.getDrawable(applicationContext, R.drawable.divider)
                        ?.let { drawable -> setDrawable(drawable) }
                }
        )

        binding.table.adapter = adapter
    }
}