package com.dikiy.workshop.ui.material.coordinator.toolbar.controller

import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.dikiy.workshop.R
import com.dikiy.workshop.ui.material.coordinator.toolbar.style.ToolbarStyle
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.tabs.TabLayout

class ToolbarController(private val activity: AppCompatActivity) : IToolbarController {

    private val toolbar: Toolbar? = activity.findViewById(R.id.toolbar)
    private val appBarLayout: AppBarLayout? = activity.findViewById(R.id.appBarLayoutView)
    private val collapsingToolbar: CollapsingToolbarLayout? = activity.findViewById(R.id.collapsedToolbarView)
    private var toolbarContainerView: ViewGroup? = null
    private val tabView: TabLayout? = activity.findViewById(R.id.tabView)

    init {
        toolbar?.let {
            activity.setSupportActionBar(it)
            it.setNavigationOnClickListener { activity.onBackPressed() }
            it.setNavigationIcon(R.drawable.ic_back)
            setTheme(IToolbarController.DEFAULT_TOOLBAR_STYLE)
            toolbarContainerView = activity.findViewById(R.id.toolbarContainerView)
        }

        hideShadow()
    }

    override fun setTheme(style: ToolbarStyle) {
        toolbar?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                it.setBackgroundColor(activity.resources.getColor(style.toolBarColor, activity.theme))
                it.setSubtitleTextColor(activity.resources.getColor(style.toolBarTextColor, activity.theme))
                it.setTitleTextColor(activity.resources.getColor(style.toolBarTextColor, activity.theme))
            }
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = activity.resources.getColor(style.statusBarColor, activity.theme)
        }
    }

    override fun setTitle(title: Int) {
        val string = activity.resources.getString(title)
        setTitle(string)
    }

    override fun setTitle(title: String) {
        collapsingToolbar?.title = title
        activity.supportActionBar?.title = title
    }

    override fun setSubTitle(subtitle: String) {
        activity.supportActionBar?.subtitle = subtitle
    }

    override fun setNavigationOnClickListener(listener: View.OnClickListener) {
        toolbar?.setNavigationOnClickListener(listener)
    }

    override fun setNavigationIcon(resourceId: Int) {
        toolbar?.setNavigationIcon(resourceId)
    }

    override fun hide() {
        activity.supportActionBar?.hide()
    }

    override fun show() {
        activity.supportActionBar?.show()
    }

    override fun hideShadow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            appBarLayout?.elevation = 0f
    }

    override fun showShadow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            appBarLayout?.elevation = 8f
    }

    override fun setLayout(@LayoutRes layoutId: Int): View {
        toolbarContainerView?.removeAllViews()
        val layoutInflater = activity.layoutInflater
        return layoutInflater.inflate(layoutId, toolbarContainerView, true)
    }

    override fun clearLayout() {
        toolbarContainerView?.removeAllViews()
    }

    override fun initTabLayout(tabs: List<String>, listener: TabLayout.OnTabSelectedListener?) {
        tabView?.let {
            it.visibility = View.VISIBLE
            for (tab in tabs) {
                val tabItem = it.newTab()
                tabItem.text = tab
                tabItem.tag = tab
                it.addTab(tabItem)
            }
            if (listener != null)
                it.addOnTabSelectedListener(listener)
        }
    }

    override fun initTabLayout(listener: ViewPager) {
        tabView?.visibility = View.VISIBLE
        tabView?.setupWithViewPager(listener)
    }

    override fun hideTabLayout() {
        tabView?.setupWithViewPager(null)
        tabView?.visibility = View.GONE
    }
}