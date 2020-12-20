package com.dikiy.workshop.ui.design.examples.first.ui.activities

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.dikiy.workshop.R
import com.dikiy.workshop.ui.design.examples.first.ui.fragments.EventsFragment
import com.dikiy.workshop.ui.design.examples.first.ui.fragments.MainFragment
import com.dikiy.workshop.ui.design.examples.first.ui.fragments.SettingsFragment
import kotlinx.android.synthetic.main.activity_purses.*
import com.dikiy.workshop.utils.style.StyleUtils

class PursesActivity : AppCompatActivity() {

    private lateinit var fragmentMain: Fragment
    private lateinit var fragmentEvents: Fragment
    private lateinit var fragmentSettings: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_purses)
        init()
    }

    private fun init() {
        initToolbar()
        initBottomNavigation()
        initFragments()
    }

    private fun initToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            StyleUtils.setStatusBarColor(this, R.color.colorPursesStatusBar)
    }

    private fun initBottomNavigation() =
            bottom_navigation.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item_bottom_nav_main -> loadFragment(fragmentMain)
                    R.id.item_bottom_nav_events -> loadFragment(fragmentEvents)
                    R.id.item_bottom_nav_settings -> loadFragment(fragmentSettings)
                }
                true
            }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }

    private fun initFragments() {
        fragmentMain = MainFragment()
        fragmentEvents = EventsFragment()
        fragmentSettings = SettingsFragment()
        loadFragment(fragmentMain)
    }
}
