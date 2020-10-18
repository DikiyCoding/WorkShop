package com.dikiy.workshop.ui.material.coordinator.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dikiy.workshop.R
import com.dikiy.workshop.ui.material.coordinator.activities.custom.BaseActivity
import kotlinx.android.synthetic.main.fragment_coordinator_custom.*

class CustomCoordinatorContentFragment : Fragment() {

    lateinit var titleToolbar: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        titleToolbar = requireActivity().getString(R.string.coordinator_toolbar_title)
        return inflater.inflate(R.layout.fragment_coordinator_custom, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupUI()
    }

    private fun setupUI() {
        initToolbar()
        search_layout.setStartIconOnClickListener { requireActivity().finish() }
    }

    private fun initToolbar(): View {
        val activity = requireActivity() as BaseActivity
        val iToolbarController = activity.iToolbarController
        if (TextUtils.isEmpty(titleToolbar)) iToolbarController.setTitle(R.string.app_name)
        else iToolbarController.setTitle(titleToolbar)
        return iToolbarController.setLayout(R.layout.searchbar)
    }

    /*fun onCreateOptionsMenu(menu: Menu?): Boolean {
        requireActivity().menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }*/
}