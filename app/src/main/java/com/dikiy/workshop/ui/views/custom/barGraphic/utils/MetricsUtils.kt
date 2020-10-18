package com.dikiy.workshop.ui.views.custom.barGraphic.utils

import android.content.Context
import android.util.TypedValue

object MetricsUtils {
    fun dpToPx(dp: Int, context: Context): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(), context.resources.displayMetrics).toInt()
    }
}