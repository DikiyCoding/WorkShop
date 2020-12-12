package com.dikiy.workshop.ui.views.android.horizontalProgressBar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dikiy.workshop.R
/*import com.dikiy.workshop.ui.views.custom.floatView.FloatView
import com.dikiy.workshop.ui.views.custom.floatView.FloatViewManager
import com.dikiy.workshop.ui.views.custom.floatView.IFloatViewManager*/

class HorizontalProgressBarFragment : Fragment() {

//    private lateinit var mFloatView: FloatView
//    private lateinit var mIFloatViewManager: IFloatViewManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mIFloatViewManager = FloatViewManager(requireActivity())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_horizontal_progress_bar, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
//        mFloatView = requireView().findViewById(R.id.view_float)
//        mIFloatViewManager.bindIFloatView(mFloatView)
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        mIFloatViewManager.unbindIFloatView(mFloatView)
    }
}
