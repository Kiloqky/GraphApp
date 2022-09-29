package ru.kiloqky.graphtableapp.utils.extensions

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.ceil

val Number.dp: Int
    get() = ceil(
        TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    ).toInt()