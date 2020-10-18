package com.dikiy.workshop.ui.design.examples.first.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dikiy.workshop.R
import kotlinx.android.synthetic.main.item_permits_header.view.*
import kotlinx.android.synthetic.main.item_permits_main.view.*
import com.dikiy.workshop.ui.design.examples.first.model.Permit
import com.dikiy.workshop.ui.design.examples.first.utils.Constants
import com.dikiy.workshop.ui.design.examples.first.utils.HorisontalFirstItemMarginDecoration

class PermitsAdapter(
        private val mCategoriesAdapter: CategoriesAdapter,
        private val listPermits: MutableList<Permit>,
        private val callback: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
            when (position) {
                0 -> 0
                else -> 1
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> ViewHolderItemHeader(inflater.inflate(R.layout.item_permits_header, parent, false))
            else -> ViewHolderItemMain(inflater.inflate(R.layout.item_permits_main, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (this.getItemViewType(position) == 1)
            (holder as ViewHolderItemMain).bind(listPermits[position - 1])
    }

    override fun getItemCount(): Int =
            listPermits.size + 1

    /**
     * Holders:
     * 1) ViewHolderItemHeader
     * 2) ViewHolderItemMain
     */

    inner class ViewHolderItemHeader(view: View) : RecyclerView.ViewHolder(view) {

        init {
            view.list_categories.adapter = mCategoriesAdapter
            view.list_categories.addItemDecoration(
                    HorisontalFirstItemMarginDecoration(Constants.MARGINS.MARGIN16.value)
            )
        }
    }

    inner class ViewHolderItemMain(view: View) : RecyclerView.ViewHolder(view) {

        private val ivIcon = view.iv_icon
        private val tvTitle = view.iv_title
        private val tvDescription = view.tv_description

        init {
            view.layout_permit.setOnClickListener { callback.invoke(adapterPosition) }
        }

        fun bind(permit: Permit) {
            ivIcon.setImageResource(permit.icon)
            tvTitle.text = permit.title
            tvDescription.text = permit.description
        }
    }
}
