package com.dikiy.workshop.ui.material.coordinator.toolbar.controller

import android.view.View

import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.viewpager.widget.ViewPager
import com.dikiy.workshop.R
import com.dikiy.workshop.ui.material.coordinator.toolbar.style.ToolbarStyle

import com.google.android.material.tabs.TabLayout

interface IToolbarController {

    fun setTheme(style: ToolbarStyle)

    fun setTitle(@StringRes title: Int)

    fun setTitle(title: String)

    fun setSubTitle(subtitle: String)

    fun setNavigationOnClickListener(listener: View.OnClickListener)

    fun setNavigationIcon(@DrawableRes resourceId: Int)

    fun hide()

    fun show()

    fun hideShadow()

    fun showShadow()

    fun setLayout(@LayoutRes layoutId: Int): View

    fun initTabLayout(tabs: List<String>, listener: TabLayout.OnTabSelectedListener?)

    fun initTabLayout(listener: ViewPager)

    fun hideTabLayout()

    fun clearLayout()

    companion object {

        // TODO: 01.03.2019 profile toolbar_custom dif then background
        val DEFAULT_TOOLBAR_STYLE = ToolbarStyle(
                R.color.colorPrimaryDark,
                R.color.colorPrimary,
                R.color.colorAccent
        )
    }
}
