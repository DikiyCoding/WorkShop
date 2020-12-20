package com.dikiy.workshop.utils.decoration

import android.graphics.Rect

import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**Начальные отступы для списков (доп. отступы для первого элемента списка) */
class HorisontalFirstItemMarginDecoration(
        private val pxMargin: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        // Вычисление пикселей по dp. Здесь отступ будет равен "pxMargin"
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.left = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    pxMargin.toFloat(),
                    view.resources.displayMetrics
            ).toInt()
    }
}
