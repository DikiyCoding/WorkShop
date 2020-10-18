package com.dikiy.workshop.ui.design.manager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.dikiy.workshop.R

class DesignAdapter(
        private val classes: MutableList<Class<*>>,
        private val callback: (Class<*>) -> Unit
) : RecyclerView.Adapter<DesignAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_main, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(classes[position])

    override fun getItemCount(): Int = classes.size

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private lateinit var className: Class<*>

        init {
            view.setOnClickListener { callback.invoke(className) }
        }

        fun bind(layout: Class<*>) {
            this.className = layout
            (view as Button).text = adapterPosition.toString()
        }
    }
}
