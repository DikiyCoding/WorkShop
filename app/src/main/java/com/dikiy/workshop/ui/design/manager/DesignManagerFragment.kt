package com.dikiy.workshop.ui.design.manager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dikiy.workshop.R
import com.dikiy.workshop.ui.design.examples.first.ui.activities.PaymentActivity
import com.dikiy.workshop.ui.design.examples.first.ui.activities.PermitsActivity
import com.dikiy.workshop.ui.design.examples.first.ui.activities.PursesActivity
import kotlinx.android.synthetic.main.fragment_manager_design.*

class DesignManagerFragment : Fragment() {

    private lateinit var mClasses: MutableList<Class<*>>
    private lateinit var mDesignAdapter: DesignAdapter

    @SuppressLint("InflateParams")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return inflater.inflate(R.layout.fragment_manager_design, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun init() {
        initList()
        initAdapter()
        assignList()
    }

    private fun initList() {
        mClasses = mutableListOf(
                PursesActivity::class.java,
                PaymentActivity::class.java,
                PermitsActivity::class.java
        )
    }

    private fun initAdapter() {
        mDesignAdapter = DesignAdapter(mClasses) { className -> navigate(className) }
    }

    private fun assignList() {
        list_layouts.adapter = mDesignAdapter
    }

    private fun navigate(className: Class<*>) {
        startActivity(Intent(requireContext(), className))
    }
}
