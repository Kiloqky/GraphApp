package ru.kiloqky.graphtableapp.presentation.points.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.data.Entry
import ru.kiloqky.graphtableapp.databinding.ItemPointBinding
import ru.kiloqky.graphtableapp.utils.EntryDIff

class PointTableAdapter : ListAdapter<Entry, PointTableAdapter.PointViewHolder>(EntryDIff) {

    inner class PointViewHolder(val binding: ItemPointBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Entry) {
            binding.xValue.text = item.x.toString()
            binding.yValue.text = item.y.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PointViewHolder(
            ItemPointBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: PointViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}