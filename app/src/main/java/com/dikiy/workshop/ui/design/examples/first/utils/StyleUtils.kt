package com.dikiy.workshop.ui.design.examples.first.utils

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

object StyleUtils {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarColor(activity: Activity, color: Int) {
        val window = activity.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(activity, color)
    }
}