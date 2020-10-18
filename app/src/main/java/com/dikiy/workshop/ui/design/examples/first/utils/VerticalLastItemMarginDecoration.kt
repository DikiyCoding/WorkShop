package com.dikiy.workshop.ui.design.examples.first.utils

import android.graphics.Rect

import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**Начальные отступы для списков (доп. отступы для первого элемента списка) */
class VerticalLastItemMarginDecoration(
        private val pxMargin: Int,
        private val listSize: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // Вычисление пикселей по dp. Здесь отступ будет равен "pxMargin"
        if (parent.getChildAdapterPosition(view) == listSize)
            outRect.bottom = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    pxMargin.toFloat(),
                    view.resources.displayMetrics
            ).toInt()
    }
}
