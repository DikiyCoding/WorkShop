package com.dikiy.workshop.code.kotlin.chips

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dikiy.workshop.R
import java.util.*

class ListsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_lists, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val array = setOf("apple", "banana", "kiwi")
        array.filter { it.startsWith("A") }
                .sortedBy { it }
                .map { it.toUpperCase(Locale.ROOT) }
                .forEach { Log.d("Logs", it) }
    }
}
