package ru.kiloqky.graphtableapp.utils

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View

fun loadBitmapFromView(view: View): Bitmap? {
    val returnedBitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(returnedBitmap)
    val bgDrawable = view.background
    if (bgDrawable != null) {
        bgDrawable.draw(canvas)
    } else {
        canvas.drawColor(Color.WHITE)
    }
    view.draw(canvas)
    return returnedBitmap
}