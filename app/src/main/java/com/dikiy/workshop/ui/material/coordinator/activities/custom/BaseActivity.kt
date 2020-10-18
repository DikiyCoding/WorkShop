package com.dikiy.workshop.ui.material.coordinator.activities.custom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dikiy.workshop.ui.material.coordinator.toolbar.controller.IToolbarController
import com.dikiy.workshop.ui.material.coordinator.toolbar.controller.ToolbarController

abstract class BaseActivity : AppCompatActivity() {

    lateinit var iToolbarController: IToolbarController
        private set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        iToolbarController = ToolbarController(this)
    }
}