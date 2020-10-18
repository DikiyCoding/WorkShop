package com.dikiy.workshop.ui.design.examples.first.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dikiy.workshop.R
import kotlinx.android.synthetic.main.item_list_category.view.*
import com.dikiy.workshop.ui.design.examples.first.model.Category

class CategoriesAdapter(
        private val categories: MutableList<Category>,
        private val callback: (Int) -> Unit
) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_list_category, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(categories[position])

    override fun getItemCount(): Int =
            categories.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivIcon = view.iv_icon
        private val tvTitle = view.iv_title
        private val layoutCategory = view.layout_category

        init {
            view.setOnClickListener { callback.invoke(adapterPosition) }
        }

        fun bind(category: Category) {
            ivIcon.setImageResource(category.icon)
            tvTitle.text = category.title
            layoutCategory.background = category.background
        }
    }
}
