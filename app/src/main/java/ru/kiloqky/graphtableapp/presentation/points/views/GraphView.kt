package ru.kiloqky.graphtableapp.presentation.points.views

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import ru.kiloqky.graphtableapp.databinding.CustomGraphViewBinding

class GraphView(context: Context, attr: AttributeSet) : FrameLayout(context, attr) {

    private var binding: CustomGraphViewBinding =
        CustomGraphViewBinding.inflate(LayoutInflater.from(context), this)

    private val xAxis by lazy { binding.chart.xAxis }
    private val yAxis by lazy { binding.chart.axisLeft }
    private val dataSet = LineDataSet(emptyList(), "")
    private val lineData = LineData()

    init {
        with(xAxis) {
            isEnabled = true
            position = XAxis.XAxisPosition.BOTTOM
            setDrawLabels(true)
            setDrawGridLines(SHOW_GRID_LINES)
            axisLineColor = Color.GRAY
            axisLineWidth = LINE_WIDTH
        }

        with(yAxis) {
            setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART)
            setDrawGridLines(SHOW_GRID_LINES)
            axisLineColor = Color.GRAY
            isEnabled = true
            axisLineWidth = LINE_WIDTH
            setDrawTopYLabelEntry(true)
            setDrawZeroLine(true)
        }

        with(binding.chart) {
            setDrawBorders(true)
            description.isEnabled = false
            setTouchEnabled(true)
            setPinchZoom(true)
            setDrawGridBackground(false)
            setScaleEnabled(true)
            isDoubleTapToZoomEnabled = true
            axisRight.isEnabled = false
            legend.isEnabled = false
            invalidate()
        }

        with(dataSet) {
            mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            cubicIntensity = CUBIC_INTENSITY
            setDrawFilled(true)
            setDrawCircles(true)
            lineWidth = LINE_WIDTH
            circleRadius = CIRCLE_RADIUS
            setDrawHorizontalHighlightIndicator(true)
        }

        with(lineData) {
            setDrawValues(true)
            setValueTextColor(Color.BLACK)
            setValueTextSize(VALUE_TEXT_SIZE)
        }
    }

    fun changePlaceMark(entryList: List<Entry>) {
        dataSet.values = entryList
        lineData.dataSets.clear()
        lineData.dataSets.add(dataSet)
        binding.chart.data = lineData
        binding.chart.data.notifyDataChanged()
        binding.chart.notifyDataSetChanged()
        binding.chart.invalidate()
    }

    companion object {
        private const val LINE_WIDTH = 2.0f
        private const val CUBIC_INTENSITY = 0.2f
        private const val CIRCLE_RADIUS = 4f
        private const val VALUE_TEXT_SIZE = 20f
        private const val SHOW_GRID_LINES = false
    }
}