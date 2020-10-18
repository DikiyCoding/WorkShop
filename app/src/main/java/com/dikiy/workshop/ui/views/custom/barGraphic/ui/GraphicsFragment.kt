package com.dikiy.workshop.ui.views.custom.barGraphic.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dikiy.workshop.R

class GraphicsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_graphics, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val graphicsView = requireView().findViewById<GraphicsView>(R.id.graphicsView)
        graphicsView.setValues(mutableListOf(15, 17, 10, 50, 43, 11, 32, 30, 23, 15))
    }
}
