package com.dikiy.workshop.ui.design.examples.first.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.dikiy.workshop.R
import kotlinx.android.synthetic.main.item_purses_header.view.*
import kotlinx.android.synthetic.main.item_purses_main.view.*
import com.dikiy.workshop.ui.design.examples.first.model.Header
import com.dikiy.workshop.ui.design.examples.first.model.Purse

class PursesAdapter(
        private val itemHeader: Header,
        private val listPurses: MutableList<Purse>,
        private val callbackHeader: (Int) -> Unit,
        private val callbackPurses: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun getItemViewType(position: Int): Int =
            when (position) {
                0 -> 0
                else -> 1
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            0 -> ViewHolderItemHeader(inflater.inflate(R.layout.item_purses_header, parent, false))
            else -> ViewHolderItemMain(inflater.inflate(R.layout.item_purses_main, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (this.getItemViewType(position) == 0) (holder as ViewHolderItemHeader).bind(itemHeader)
        else (holder as ViewHolderItemMain).bind(listPurses[position - 1])
    }

    override fun getItemCount(): Int =
            listPurses.size + 1

    /**
     * Holders:
     * 1) ViewHolderItemHeader
     * 2) ViewHolderItemMain
     */

    inner class ViewHolderItemHeader(view: View) : RecyclerView.ViewHolder(view) {

        private val ibInfo = view.ib_info
        private val tvAccount = view.tv_account
        private val tvDetails = view.tv_details
        private val btnMoneyPickUp = view.btn_money_pick_up
        private val ivUpcoming = view.iv_upcoming
        private val tvTitleUpcoming = view.tv_title_upcoming
        private val tvValueUpcoming = view.tv_value_upcoming
        private val layoutUpcoming = view.layout_upcoming

        init {
            ibInfo.setOnClickListener { callbackHeader.invoke(ibInfo.id) }
            tvDetails.setOnClickListener { callbackHeader.invoke(tvDetails.id) }
            btnMoneyPickUp.setOnClickListener { callbackHeader.invoke(btnMoneyPickUp.id) }
        }

        fun bind(header: Header) {
            tvAccount.text = header.account
            if (header.isUpcoming) {
                layoutUpcoming.visibility = View.VISIBLE
                ivUpcoming.setImageResource(header.icon)
                tvTitleUpcoming.text = header.title
                tvValueUpcoming.text = header.value
            }
        }
    }

    inner class ViewHolderItemMain(view: View) : RecyclerView.ViewHolder(view) {

        private val tvDate = view.tv_date
        private val ivOperation = view.iv_operation
        private val tvTitle = view.tv_title_operation
        private val tvValue = view.tv_value_operation

        init {
            view.setOnClickListener { callbackPurses.invoke(adapterPosition) }
        }

        fun bind(purse: Purse) {
            if (purse.date.isNullOrEmpty()) tvDate.visibility = View.GONE
            else tvDate.text = purse.date
            ivOperation.setImageResource(purse.icon)
            tvTitle.text = purse.title
            tvValue.setTextColor(purse.color)
            tvValue.text = purse.value
        }
    }
}
