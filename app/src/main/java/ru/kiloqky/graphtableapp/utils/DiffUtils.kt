package ru.kiloqky.graphtableapp.utils

import androidx.recyclerview.widget.DiffUtil
import com.github.mikephil.charting.data.Entry


val EntryDIff: DiffUtil.ItemCallback<Entry>
    get() = object : DiffUtil.ItemCallback<Entry>() {
        override fun areItemsTheSame(oldItem: Entry, newItem: Entry) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Entry, newItem: Entry) =
            oldItem.x == newItem.x && oldItem.y == newItem.y
    }

